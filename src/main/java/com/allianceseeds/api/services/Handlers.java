package com.allianceseeds.api.services;

import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.category.*;
import com.allianceseeds.api.domain.commands.notifications.SendEmailCommand;
import com.allianceseeds.api.domain.commands.product.*;
import com.allianceseeds.api.domain.commands.user.AddUserCommand;
import com.allianceseeds.api.domain.commands.user.AuthenticateUserCommand;
import com.allianceseeds.api.domain.commands.user.DeleteUserByNameCommand;
import com.allianceseeds.api.services.category.CategoryHandler;
import com.allianceseeds.api.services.notifications.NotificationHandler;
import com.allianceseeds.api.services.product.ProductHandler;
import com.allianceseeds.api.services.user.UserHandler;
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

    private final UserHandler userHandler;


    @Autowired
    public Handlers(NotificationHandler notificationHandler, ProductHandler productHandler, CategoryHandler categoryHandler, UserHandler userHandler) {

        this.notificationHandler = notificationHandler;
        this.productHandler = productHandler;
        this.categoryHandler = categoryHandler;
        this.userHandler = userHandler;


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
        commandHandlers.put(GetProductByProdCodeCommand.class, this.productHandler::getProductByProdCode);



        // Category
        commandHandlers.put(AddCategoryCommand.class, this.categoryHandler::addCategory);
        commandHandlers.put(DeleteCategoryByNameCommand.class, this.categoryHandler::deleteCategoryByName);
        commandHandlers.put(GetAllCategoriesCommand.class, this.categoryHandler::getAllCategories);
        commandHandlers.put(GetCategoryNamesCommand.class, this.categoryHandler::getCategoryNames);
        commandHandlers.put(GetCategoryByNameCommand.class, this.categoryHandler::getCategoryByName);
        commandHandlers.put(UpdateCategoryCommand.class, this.categoryHandler::updateCategory);


        // User
        commandHandlers.put(AddUserCommand.class, this.userHandler::addUser);
        commandHandlers.put(DeleteUserByNameCommand.class, this.userHandler::deleteUserByName);
        commandHandlers.put(AuthenticateUserCommand.class, this.userHandler::authenticate);







    }

    public Map<Class<? extends Command>, Function<Command, Transformer>> getCmdHandlers(){
        return commandHandlers;
    }


}
