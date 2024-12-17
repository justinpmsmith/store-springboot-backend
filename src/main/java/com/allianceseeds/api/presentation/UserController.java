package com.allianceseeds.api.presentation;

import com.allianceseeds.api.domain.commands.user.AuthenticateUserCommand;
import com.allianceseeds.api.domain.entities.User;
import com.allianceseeds.api.presentation.resources.UserResource;
import com.allianceseeds.api.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserResource userResource;

    @Autowired
    public UserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @PostMapping("/client/addUser")
    public Transformer addUser(@RequestBody User user) {
        return userResource.addUser(user);
    }

    @DeleteMapping("/client/deleteUserByName")
    public Transformer deleteUserByName(@RequestParam String name) {
        return userResource.deleteUserByName(name);
    }

    @PostMapping("/client/authenticateUser")
    public Transformer authenticateUser(@RequestBody AuthenticateUserCommand command) {
        return userResource.authenticateUser(command);
    }

}