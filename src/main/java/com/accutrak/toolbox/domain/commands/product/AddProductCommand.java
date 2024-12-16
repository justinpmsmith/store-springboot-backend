package com.accutrak.toolbox.domain.commands.product;

import com.accutrak.toolbox.domain.entities.Product;
import lombok.Getter;

@Getter
public class AddProductCommand implements ProductCommand{
    private Product product;

    public AddProductCommand(Product product) {
        this.product = product;
    }
}
