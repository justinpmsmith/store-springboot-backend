package com.allianceseeds.api.domain.commands.category;

import lombok.Getter;

@Getter
public class DeleteCategoryByNameCommand implements CategoryCommand {
    private String name;

    public DeleteCategoryByNameCommand(String name) {
        this.name = name.toUpperCase();
    }
}