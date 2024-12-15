package com.accutrak.toolbox.domain.commands.user;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetUuidByEmailCommand implements UserCommand {

    String email;

    public GetUuidByEmailCommand(String email) {
        Validation.validateEmail(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
