package com.accutrak.toolbox.domain.commands.user;

import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.domain.exceptions.InvalidLengthException;
import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class AddUserCommand implements UserCommand {
    private final User user;

    public AddUserCommand(User user) {
        Validation.validateUser(user);
        this.user = user;
    }


}

