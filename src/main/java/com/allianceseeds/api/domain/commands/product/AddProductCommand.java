package com.allianceseeds.api.domain.commands.product;

import com.allianceseeds.api.domain.entities.Product;
import lombok.Getter;

@Getter
public class AddProductCommand implements ProductCommand{
    private Product product;

    public AddProductCommand(Product product) {
        product.setCategory(product.getCategory().toUpperCase());
        this.product = product;
    }
}
