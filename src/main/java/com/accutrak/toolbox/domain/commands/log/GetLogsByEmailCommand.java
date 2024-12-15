package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.exceptions.InvalidEmailException;
import com.accutrak.toolbox.domain.validation.Validation;

import java.util.Arrays;

public class GetLogsByEmailCommand implements LogCommand{
    private  String email;

    public GetLogsByEmailCommand(String email) {
        Validation.validateEmail(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}
