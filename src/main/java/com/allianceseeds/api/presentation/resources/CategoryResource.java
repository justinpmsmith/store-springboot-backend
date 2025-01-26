package com.allianceseeds.api.presentation.resources;


import com.allianceseeds.api.domain.commands.category.*;
import com.allianceseeds.api.domain.entities.Category;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class CategoryResource extends BaseResource {

    public CategoryResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addCategory(Category category) {
        AddCategoryCommand command = new AddCategoryCommand(category);
        return messageBus.publishCommand(command);
    }
    public Transformer updateCategory(Category category) {
        UpdateCategoryCommand command = new UpdateCategoryCommand(category);
        return messageBus.publishCommand(command);
    }

    public Transformer deleteCategoryByName(String name) {
        DeleteCategoryByNameCommand command = new DeleteCategoryByNameCommand(name);
        return messageBus.publishCommand(command);
    }

    public Transformer getAllCategories() {
        GetAllCategoriesCommand command = new GetAllCategoriesCommand();
        return messageBus.publishCommand(command);
    }

    public Transformer getCategoryNames() {
        GetCategoryNamesCommand command = new GetCategoryNamesCommand();
        return messageBus.publishCommand(command);
    }

    public Transformer getCategoryByName(String name) {
        GetCategoryByNameCommand command = new GetCategoryByNameCommand(name);
        return messageBus.publishCommand(command);
    }
}
