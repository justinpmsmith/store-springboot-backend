package com.allianceseeds.api.domain.commands.category;

import com.allianceseeds.api.domain.entities.Category;
import lombok.Getter;

@Getter
public class UpdateCategoryCommand implements CategoryCommand{
    Category category;

    public UpdateCategoryCommand(Category category) {
        this.category = category;
    }
}
