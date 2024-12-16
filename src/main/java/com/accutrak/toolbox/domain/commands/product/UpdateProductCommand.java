package com.accutrak.toolbox.domain.commands.product;

import com.accutrak.toolbox.domain.entities.Product;
import lombok.Getter;

@Getter
public class UpdateProductCommand implements ProductCommand{
    private Product product;

    public UpdateProductCommand(Product product) {
        this.product = product;
    }
}
