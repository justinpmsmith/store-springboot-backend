package com.allianceseeds.api.domain.commands.user;

import lombok.Getter;

@Getter
public class DeleteUserByNameCommand implements UserCommand {
    private String name;

    public DeleteUserByNameCommand(String name) {
        this.name = name;
    }
}