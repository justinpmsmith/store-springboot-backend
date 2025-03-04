package com.allianceseeds.api.services.product;

import com.allianceseeds.api.adapters.notifications.EmailAdapter;
import com.allianceseeds.api.adapters.repositories.product.ProductRepo;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.product.*;
import com.allianceseeds.api.domain.entities.Product;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.UnitOfWork;
import com.allianceseeds.api.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHandler {

    private final ProductRepo productRepo;

    private final UnitOfWork<Product> productUOW;

    private final EmailAdapter emailAdapter;


    @Autowired
    public ProductHandler(ProductRepo productRepo, EmailAdapter emailAdapter) {
        this.productRepo = productRepo;
        this.emailAdapter = emailAdapter;
        this.productUOW = new UnitOfWork<Product>(this.productRepo);
    }

    public Transformer addProduct(Command command){
        Product product = ((AddProductCommand) command).getProduct();

        List<Product> productList  = productRepo.getProductsByStringField("prodCode",product.getProdCode());
        System.out.println("price: " + product.getPrice());

        if (!productList.isEmpty() ){
            ProductTransformer transformer = new ProductTransformer<>(false, null);
            transformer.setMessage("There is another product with this product code");
            return transformer;
        }
        productUOW.registerRepoOperation(product, UnitOfWorkInt.UnitActions.INSERT);
        productUOW.commit();

        return new ProductTransformer<Product>(true, null);
    }
    public Transformer deleteProductByProductCode(Command command){
        String prodCode = ((DeleteProductByProdCodeCommand) command).getProdCode();
        List<Product> products = productRepo.getProductsByStringField("prodCode", prodCode);

        if(products.size() > 0){
            productUOW.registerBulkOperation(products, UnitOfWorkInt.UnitActions.DELETE);
            productUOW.commit();
        }

        return new ProductTransformer<>(true, null);
    }

    public Transformer updateProduct(Command command){
        Product product = ((UpdateProductCommand) command).getProduct();
        List<Product> productList  = productRepo.getProductsByStringField("prodCode",product.getProdCode());

        if(productList.size() > 0){
            Long id = productList.get(0).getId();
            product.setId(id);
        }

        productUOW.registerRepoOperation(product, UnitOfWorkInt.UnitActions.INSERT);
        productUOW.commit();

        return new ProductTransformer<Product>(true, null);

    }

    public Transformer getProductByProdCode(Command command){
        String prodCode = ((GetProductByProdCodeCommand) command).getProdCode();
        List<Product> prodList = productRepo.getProductsByStringField("prodCode", prodCode);

        if(!prodList.isEmpty()){
            Product product = prodList.get(0);
            return new ProductTransformer<>(true, product);

        }
        return new ProductTransformer<>(true, null);

    }


    public Transformer getProductsByCategory(Command command){
        String category = ((GetProductsByCategoryCommand) command).getCategory();
        List<Product> prodList = productRepo.getProductsByStringField("category", category);

        // Filter out products with quantity <= 0
        List<Product> inStockProducts = prodList.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

        return new ProductTransformer<>(true, inStockProducts);
    }

    public Transformer getAllProducts(Command command){
        List<Product> products = productRepo.getAll();

        // Filter out products with quantity <= 0
        List<Product> inStockProducts = products.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

        return new ProductTransformer<>(true, inStockProducts);
    }


    public Transformer deleteProductsByCategory(Command command){
        String category = ((DeleteProductsByCategoryCommand) command).getCategory();
        List<Product> products = productRepo.getProductsByStringField("category", category);

        if(products.size() > 0){
            productUOW.registerBulkOperation(products, UnitOfWorkInt.UnitActions.DELETE);
            productUOW.commit();
        }

        return new ProductTransformer<>(true, null);
    }

    public Transformer handleSoldProducts(Command command) {
        ProductsSoldCommand soldCommand = (ProductsSoldCommand) command;

        // Get the list of products from the command
        List<Product> soldProducts = soldCommand.getProducts();

        // Create a list of product codes
        List<String> prodCodes = soldProducts.stream()
                .map(Product::getProdCode)
                .collect(Collectors.toList());

        // Get the products from the repository
        List<Product> productsFromDB = productRepo.getProductsByProdCodes(prodCodes);

        // Create lists for products to delete and update
        List<Product> productsToDelete = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();

        // Process each product from the database
        for (Product product : productsFromDB) {
            if (product.getQuantity() <= 1) {
                // If quantity is 1 or less, add to delete list
                productsToDelete.add(product);
            } else {
                // If quantity is more than 1, decrement and add to update list
                product.setQuantity(product.getQuantity() - 1);
                productsToUpdate.add(product);
            }
        }

        // Delete products with quantity 0 or 1
        if (!productsToDelete.isEmpty()) {
            productUOW.registerBulkOperation(productsToDelete, UnitOfWorkInt.UnitActions.DELETE);
            productUOW.commit();
        }

        // Update products with decremented quantities
        if (!productsToUpdate.isEmpty()) {
            productUOW.registerBulkOperation(productsToUpdate, UnitOfWorkInt.UnitActions.INSERT);
            productUOW.commit();
        }

        // Send email receipt and get the result
        boolean emailSent = sendPurchaseConfirmationEmail(soldCommand, productsFromDB);

        return new ProductTransformer<>(emailSent, null);
    }

    private boolean sendPurchaseConfirmationEmail(ProductsSoldCommand command, List<Product> products) {
        // TODO: Use env variables for admin email and cell
        String adminEmail = "justinpmsmith@outlook.com";
        String adminCell = "123-456-7890";

        // Prepare recipient list (admin and customer)
        String[] recipients = {adminEmail};

        // If customer email is provided, add it to recipients
        if (command.getEmail() != null && !command.getEmail().isEmpty()) {
            recipients = new String[]{adminEmail, command.getEmail()};
        }

        // Create subject line
        String subject = "Alliance Seeds Purchase Confirmation";

        // Create the email body
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Dear ").append(command.getName()).append(",\n\n");
        bodyBuilder.append("Thank you for your purchase.\n\n");

        // Add shipping information if available
        if (command.getAddress() != null && !command.getAddress().isEmpty()) {
            bodyBuilder.append("If you have chosen to collect your order we will be in contact shortly to make arrangements; ")
                    .append("Otherwise you can expect your order 2-5 working days at the location you provided.\n");
            bodyBuilder.append("\nLocation: ").append(command.getAddress()).append("\n");

            // Add customer contact info
            if (command.getCell() != null && !command.getCell().isEmpty()) {
                bodyBuilder.append("Contact Cell: ").append(command.getCell()).append("\n");
            }

            // Add customer email if available
            if (command.getEmail() != null && !command.getEmail().isEmpty()) {
                bodyBuilder.append("Contact Email: ").append(command.getEmail()).append("\n");
            }
            bodyBuilder.append("\n");
        }

        // List purchased products
        bodyBuilder.append("Items purchased:\n");

        for (Product product : products) {
            bodyBuilder.append("- ").append(product.getName())
                    .append(": R").append(String.format("%.2f", product.getPrice()))
                    .append("\n");
        }

        // Add shipping fee and total
        Long shippingFee = command.getShipping_fee() != null ? command.getShipping_fee() : 0L;
        bodyBuilder.append("\nShipping fee: R").append(String.format("%.2f", shippingFee.floatValue()));

        // Use the total price from the command
        if (command.getTotalPrice() != null) {
            bodyBuilder.append("\nTotal: R").append(String.format("%.2f", command.getTotalPrice()));
        }

        // Add contact information
        bodyBuilder.append("\n\nIf you have any queries please contact us at:\n");
        bodyBuilder.append("Email: ").append(adminEmail).append("\n");
        bodyBuilder.append("Cell: ").append(adminCell).append("\n\n");
        bodyBuilder.append("Thank you for your support");

        // Send the email and return the result
        return emailAdapter.sendEmail(recipients, subject, bodyBuilder.toString());
    }
}
