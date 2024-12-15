package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetLogsByEmailFromToCommand implements LogCommand{
    private  String email;
    private  int to;
    private int from;

    public GetLogsByEmailFromToCommand(String email, int to, int from) {
        Validation.validateEmail(email);
        this.email = email;
        this.to = to;
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }


}
