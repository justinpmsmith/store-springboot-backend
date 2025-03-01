package com.allianceseeds.api.domain.commands.customer;

import lombok.Getter;

@Getter
public class SellSomethingCommand implements CustomerCommand{
    private String name;
    private String surname;
    private String cell;
    private String email;
    private String description;
    private String[] images;
    private String price;

    public SellSomethingCommand(String name, String surname, String cell, String email, String description, String[] images, String price) {
        this.name = name;
        this.surname = surname;
        this.cell = cell;
        this.email = email;
        this.description = description;
        this.images = images;
        this.price = price;
    }
}
