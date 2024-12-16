package com.accutrak.toolbox.domain.commands.category;

import com.accutrak.toolbox.domain.entities.Category;
import lombok.Getter;

@Getter
public class AddCategoryCommand implements CategoryCommand {
    private Category category;

    public AddCategoryCommand(Category category) {
        this.category = category;
        this.category.setName(category.getName().toUpperCase());
    }
}