package com.allianceseeds.api.services.pendingsale;

import com.allianceseeds.api.adapters.notifications.EmailAdapter;
import com.allianceseeds.api.adapters.repositories.pendingsale.PendingSaleRepo;
import com.allianceseeds.api.adapters.repositories.product.ProductRepo;
import com.allianceseeds.api.adapters.repositories.soldProducts.SoldProductRepo;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.pendingsale.CreatePendingSaleCommand;
import com.allianceseeds.api.domain.commands.pendingsale.GetAllPendingSalesCommand;
import com.allianceseeds.api.domain.commands.pendingsale.ProcessPaymentNotificationCommand;
import com.allianceseeds.api.domain.entities.PendingSale;
import com.allianceseeds.api.domain.entities.Product;
import com.allianceseeds.api.domain.entities.SoldProduct;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.UnitOfWork;
import com.allianceseeds.api.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PendingSaleHandler {

    private final PendingSaleRepo pendingSaleRepo;
    private final UnitOfWork<PendingSale> pendingSaleUOW;

    private final ProductRepo productRepo;
    private final UnitOfWork<Product> productUOW;

    private final SoldProductRepo soldProductRepo;
    private final UnitOfWork<SoldProduct> soldProductUOW;

    private final EmailAdapter emailAdapter;

    @Value("${env.ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${env.BACKUP_ADMIN_EMAIL}")
    private String backupAdminEmail;

    private final ObjectMapper objectMapper;

    @Autowired
    public PendingSaleHandler(PendingSaleRepo pendingSaleRepo, ProductRepo productRepo,
                              SoldProductRepo soldProductRepo, EmailAdapter emailAdapter) {
        this.pendingSaleRepo = pendingSaleRepo;
        this.pendingSaleUOW = new UnitOfWork<>(this.pendingSaleRepo);

        this.productRepo = productRepo;
        this.productUOW = new UnitOfWork<>(this.productRepo);

        this.soldProductRepo = soldProductRepo;
        this.soldProductUOW = new UnitOfWork<>(this.soldProductRepo);

        this.emailAdapter = emailAdapter;

        this.objectMapper = new ObjectMapper();
    }

    public Transformer createPendingSale(Command command) {
        CreatePendingSaleCommand createCommand = (CreatePendingSaleCommand) command;

        // Check if there's already a pending sale with this payment ID
        PendingSale existingPendingSale = pendingSaleRepo.findByPaymentId(createCommand.getPaymentId());
        if (existingPendingSale != null) {
            PendingSaleTransformer<PendingSale> transformer = new PendingSaleTransformer<>(false, null);
            transformer.setMessage("A pending sale with this payment ID already exists");
            return transformer;
        }

        // Create a new PendingSale entity
        PendingSale pendingSale = PendingSale.builder()
                .paymentId(createCommand.getPaymentId())
                .name(createCommand.getName())
                .address(createCommand.getAddress())
                .totalPrice(createCommand.getTotalPrice())
                .shipping_fee(createCommand.getShipping_fee())
                .prodCodes(createCommand.getProdCodes())
                .email(createCommand.getEmail())
                .cell(createCommand.getCell())
                .build();

        // Save the entity
        pendingSaleUOW.registerRepoOperation(pendingSale, UnitOfWorkInt.UnitActions.INSERT);
        pendingSaleUOW.commit();

        return new PendingSaleTransformer<>(true, pendingSale);
    }

    public Transformer getAllPendingSales(Command command) {
        List<PendingSale> pendingSales = pendingSaleRepo.getAll();
        return new PendingSaleTransformer<>(true, pendingSales);
    }

    public Transformer processPaymentNotification(Command command) {
        ProcessPaymentNotificationCommand notificationCommand = (ProcessPaymentNotificationCommand) command;

        // Extract key information from notification
        String paymentId = notificationCommand.getM_payment_id();
        String paymentStatus = notificationCommand.getPayment_status();

        // Only process completed payments
        if (!"COMPLETE".equals(paymentStatus)) {
            PendingSaleTransformer<String> transformer = new PendingSaleTransformer<>(false, null);
            transformer.setMessage("Payment status is not COMPLETE: " + paymentStatus);
            return transformer;
        }

        // Find the pending sale
        PendingSale pendingSale = pendingSaleRepo.findByPaymentId(paymentId);
        if (pendingSale == null) {
            PendingSaleTransformer<String> transformer = new PendingSaleTransformer<>(false, null);
            transformer.setMessage("No pending sale found for payment ID: " + paymentId);
            return transformer;
        }

        try {
            // Parse the prodCodes string to get an array of product codes
            String[] prodCodesArray = objectMapper.readValue(pendingSale.getProdCodes(), String[].class);
            List<String> prodCodes = Arrays.asList(prodCodesArray);

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

            // Record sold products in the SoldProducts table
            recordSoldProducts(pendingSale, productsFromDB);

            // Send email receipt and get the result
            boolean emailSent = sendPurchaseConfirmationEmail(pendingSale, productsFromDB);

            // Delete the pending sale since it's now completed
            List<PendingSale> toDelete = new ArrayList<>();
            toDelete.add(pendingSale);
            pendingSaleUOW.registerBulkOperation(toDelete, UnitOfWorkInt.UnitActions.DELETE);
            pendingSaleUOW.commit();

            return new PendingSaleTransformer<>(emailSent, null);

        } catch (JsonProcessingException e) {
            PendingSaleTransformer<String> transformer = new PendingSaleTransformer<>(false, null);
            transformer.setMessage("Error processing product codes: " + e.getMessage());
            return transformer;
        }
    }

    private void recordSoldProducts(PendingSale pendingSale, List<Product> products) {
        List<SoldProduct> soldProducts = new ArrayList<>();

        for (Product product : products) {
            // Create a new SoldProduct entity for each product
            // Get customer address from pendingSale, use "NA" if not available
            String customerAddress = (pendingSale.getAddress() != null && !pendingSale.getAddress().isEmpty())
                    ? pendingSale.getAddress()
                    : "NA";

            SoldProduct soldProduct = SoldProduct.builder()
                    .prodCode(product.getProdCode())
                    .category(product.getCategory())
                    .name(product.getName())
                    .photos(product.getPhotos())
                    .price(product.getPrice())
                    .document(product.getDocument())
                    .info(product.getInfo())
                    .quantity(1) // Always 1 for each product
                    .customerName(pendingSale.getName())
                    .customerEmail(pendingSale.getEmail() != null ? pendingSale.getEmail() : "NA")
                    .customerAddress(customerAddress)
                    .build();

            soldProducts.add(soldProduct);
        }

        // Save all sold products to the database
        if (!soldProducts.isEmpty()) {
            soldProductUOW.registerBulkOperation(soldProducts, UnitOfWorkInt.UnitActions.INSERT);
            soldProductUOW.commit();
        }
    }

    private boolean sendPurchaseConfirmationEmail(PendingSale pendingSale, List<Product> products) {
        // TODO: Use env variables for admin email and cell
        String adminCell = "084 507 3080";

        // Prepare recipient list (admin and customer)
        String[] recipients = {adminEmail, backupAdminEmail};

        // If customer email is provided, add it to recipients
        if (pendingSale.getEmail() != null && !pendingSale.getEmail().isEmpty()) {
            recipients = new String[]{adminEmail, backupAdminEmail, pendingSale.getEmail()};
        }

        // Create subject line
        String subject = "Sportvest Purchase Confirmation";

        // Create the email body
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Dear ").append(pendingSale.getName()).append(",\n\n");
        bodyBuilder.append("Thank you for your purchase.\n\n");

        // Add shipping information if available
        if (pendingSale.getAddress() != null && !pendingSale.getAddress().isEmpty()) {
            bodyBuilder.append("If you have chosen to collect your order we will be in contact shortly to make arrangements; ")
                    .append("Otherwise you can expect your order in 2-5 working days at the location you provided.\n");
            bodyBuilder.append("\nLocation: ").append(pendingSale.getAddress()).append("\n");

            // Add customer contact info
            if (pendingSale.getCell() != null && !pendingSale.getCell().isEmpty()) {
                bodyBuilder.append("Contact Cell: ").append(pendingSale.getCell()).append("\n");
            }

            // Add customer email if available
            if (pendingSale.getEmail() != null && !pendingSale.getEmail().isEmpty()) {
                bodyBuilder.append("Contact Email: ").append(pendingSale.getEmail()).append("\n");
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
        Long shippingFee = pendingSale.getShipping_fee() != null ? pendingSale.getShipping_fee() : 0L;
        bodyBuilder.append("\nShipping fee: R").append(String.format("%.2f", shippingFee.floatValue()));

        // Use the total price from the pendingSale
        if (pendingSale.getTotalPrice() != null) {
            bodyBuilder.append("\nTotal: R").append(String.format("%.2f", pendingSale.getTotalPrice()));
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