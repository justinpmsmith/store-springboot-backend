package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetTagConfigsByEmailCommand implements TagConfigCommand{

    private String email;

    public GetTagConfigsByEmailCommand(String email) {
        Validation.validateEmail(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
