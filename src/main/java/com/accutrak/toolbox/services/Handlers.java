package com.accutrak.toolbox.services;

import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.category.*;
import com.accutrak.toolbox.domain.commands.notifications.SendEmailCommand;
import com.accutrak.toolbox.domain.commands.product.*;
import com.accutrak.toolbox.services.category.CategoryHandler;
import com.accutrak.toolbox.services.notifications.NotificationHandler;
import com.accutrak.toolbox.services.product.ProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class Handlers {

    private final Map<Class<? extends Command>, Function<Command, Transformer>> commandHandlers;

    private final NotificationHandler notificationHandler;

    private final ProductHandler productHandler;

    private final CategoryHandler categoryHandler;


    @Autowired
    public Handlers(NotificationHandler notificationHandler, ProductHandler productHandler, CategoryHandler categoryHandler) {

        this.notificationHandler = notificationHandler;
        this.productHandler = productHandler;
        this.categoryHandler = categoryHandler;


        commandHandlers = new HashMap<>();


        // notifications
        commandHandlers.put(SendEmailCommand.class, this.notificationHandler::sendEmail);


        // products
        commandHandlers.put(AddProductCommand.class, this.productHandler::addProduct);
        commandHandlers.put(DeleteProductsByCategoryCommand.class, this.productHandler::deleteProductsByCategory);
        commandHandlers.put(DeleteProductByProdCodeCommand.class, this.productHandler::deleteProductByProductCode);
        commandHandlers.put(GetAllProductsCommand.class, this.productHandler::getAllProducts);
        commandHandlers.put(GetProductsByCategoryCommand.class, this.productHandler::getProductsByCategory);
        commandHandlers.put(UpdateProductCommand.class, this.productHandler::updateProduct);


        // Category
        commandHandlers.put(AddCategoryCommand.class, this.categoryHandler::addCategory);
        commandHandlers.put(DeleteCategoryByNameCommand.class, this.categoryHandler::deleteCategoryByName);
        commandHandlers.put(GetAllCategoriesCommand.class, this.categoryHandler::getAllCategories);
        commandHandlers.put(GetCategoryNamesCommand.class, this.categoryHandler::getCategoryNames);
        commandHandlers.put(GetCategoryByNameCommand.class, this.categoryHandler::getCategoryByName);






    }

    public Map<Class<? extends Command>, Function<Command, Transformer>> getCmdHandlers(){
        return commandHandlers;
    }


}
