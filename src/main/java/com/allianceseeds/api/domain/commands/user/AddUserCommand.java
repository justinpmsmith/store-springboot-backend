package com.allianceseeds.api.domain.commands.user;

import com.allianceseeds.api.domain.entities.User;
import lombok.Getter;

@Getter
public class AddUserCommand implements UserCommand {
    private User user;

    public AddUserCommand(User user) {
        this.user = user;
    }
}