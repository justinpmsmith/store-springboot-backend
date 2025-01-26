package com.allianceseeds.api.services.product;

import com.allianceseeds.api.adapters.repositories.product.ProductRepo;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.product.*;
import com.allianceseeds.api.domain.entities.Product;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.UnitOfWork;
import com.allianceseeds.api.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHandler {

    private final ProductRepo productRepo;

    private final UnitOfWork<Product> productUOW;

    @Autowired
    public ProductHandler(ProductRepo productRepo) {
        this.productRepo = productRepo;
        this.productUOW = new UnitOfWork<Product>(this.productRepo);
    }

    public Transformer addProduct(Command command){
        Product product = ((AddProductCommand) command).getProduct();

        List<Product> productList  = productRepo.getProductsByStringField("prodCode",product.getProdCode());

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
        // TODO: handle out of stock products
        String category = ((GetProductsByCategoryCommand) command).getCategory();
        List<Product> prodList= productRepo.getProductsByStringField("category", category);

        return new ProductTransformer<>(true, prodList);
    }

    public Transformer getAllProducts(Command command){
        List<Product> products = productRepo.getAll();
        return new ProductTransformer<>(true, products);
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

}
