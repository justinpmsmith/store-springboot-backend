package com.allianceseeds.api.domain.commands.product;

import com.allianceseeds.api.domain.entities.Product;
import lombok.Getter;

@Getter
public class UpdateProductCommand implements ProductCommand{
    private Product product;

    public UpdateProductCommand(Product product) {
        this.product = product;
    }
}
