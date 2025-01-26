package com.allianceseeds.api.domain.commands.product;

import lombok.Getter;

@Getter
public class GetProductsByCategoryCommand implements ProductCommand{
    private String Category;

    public GetProductsByCategoryCommand(String category) {
        Category = category.toUpperCase();
    }
}
