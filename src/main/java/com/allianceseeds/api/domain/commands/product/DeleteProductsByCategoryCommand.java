package com.allianceseeds.api.domain.commands.product;

import lombok.Getter;

@Getter
public class DeleteProductsByCategoryCommand implements ProductCommand{
    private String category;

    public DeleteProductsByCategoryCommand(String category) {
        this.category = category;
    }
}
