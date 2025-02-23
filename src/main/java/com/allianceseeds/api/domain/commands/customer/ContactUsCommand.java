package com.allianceseeds.api.domain.commands.customer;

import lombok.Getter;

@Getter
public class ContactUsCommand implements CustomerCommand{
    private String name;
    private String surname;
    private String cell;
    private String email;
    private String message;


    public ContactUsCommand(String name, String surname, String cell, String email, String message) {
        this.name = name;
        this.surname = surname;
        this.cell = cell;
        this.email = email;
        this.message = message;
    }
}
