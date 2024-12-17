package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.domain.commands.user.AddUserCommand;
import com.allianceseeds.api.domain.commands.user.AuthenticateUserCommand;
import com.allianceseeds.api.domain.commands.user.DeleteUserByNameCommand;
import com.allianceseeds.api.domain.entities.User;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class UserResource extends BaseResource {

    public UserResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addUser(User user) {
        AddUserCommand command = new AddUserCommand(user);
        return messageBus.publishCommand(command);
    }

    public Transformer deleteUserByName(String name) {
        DeleteUserByNameCommand command = new DeleteUserByNameCommand(name);
        return messageBus.publishCommand(command);
    }

    public Transformer authenticateUser(AuthenticateUserCommand command) {
        return messageBus.publishCommand(command);
    }
}