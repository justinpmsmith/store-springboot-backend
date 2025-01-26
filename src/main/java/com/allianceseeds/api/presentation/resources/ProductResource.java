package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.domain.commands.product.*;
import com.allianceseeds.api.domain.entities.Product;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class ProductResource extends BaseResource{
    public ProductResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addProduct(Product product){
        AddProductCommand command = new AddProductCommand(product);
        return messageBus.publishCommand(command);
    }

    public Transformer deleteProductByProdCode(String prodCode){
        DeleteProductByProdCodeCommand command = new DeleteProductByProdCodeCommand(prodCode);
        return messageBus.publishCommand(command);
    }

    public Transformer updateProduct(Product product){
        UpdateProductCommand command = new UpdateProductCommand(product);
        return messageBus.publishCommand(command);
    }

    public Transformer getProductByProdCode(String prodCode){
        GetProductByProdCodeCommand command = new GetProductByProdCodeCommand(prodCode);
        return messageBus.publishCommand(command);
    }

    public Transformer getProductsByCategory(String category){
        GetProductsByCategoryCommand command = new GetProductsByCategoryCommand(category);
        return messageBus.publishCommand(command);
    }

    public Transformer getAllProducts(){
        GetAllProductsCommand command = new GetAllProductsCommand();
        return messageBus.publishCommand(command);
    }

    public Transformer deleteProductByCategory(String category){

        DeleteProductsByCategoryCommand command = new DeleteProductsByCategoryCommand(category);
        return messageBus.publishCommand(command);
    }
}
