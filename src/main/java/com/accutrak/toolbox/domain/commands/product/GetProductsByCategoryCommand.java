package com.accutrak.toolbox.domain.commands.product;

import lombok.Getter;

@Getter
public class GetProductsByCategoryCommand implements ProductCommand{
    private String Category;

    public GetProductsByCategoryCommand(String category) {
        Category = category;
    }
}
