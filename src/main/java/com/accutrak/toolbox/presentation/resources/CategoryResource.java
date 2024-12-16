package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.category.*;
import com.accutrak.toolbox.domain.entities.Category;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
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
