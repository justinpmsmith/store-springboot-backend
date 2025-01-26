package com.allianceseeds.api.domain.commands.category;

import lombok.Getter;
@Getter
public class GetCategoryByNameCommand implements CategoryCommand {
    private String name;

    public GetCategoryByNameCommand(String name) {
        this.name = name.toUpperCase();
    }
}
