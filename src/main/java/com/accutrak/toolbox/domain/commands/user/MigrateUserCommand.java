package com.accutrak.toolbox.domain.commands.user;

import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class MigrateUserCommand implements UserCommand{
    private final User user;

    public MigrateUserCommand(User user) {
        Validation.validateDeviceId(user.getDeviceId());
//        Validation.validateEmail(user.getEmail());
        this.user = user;
    }
}
