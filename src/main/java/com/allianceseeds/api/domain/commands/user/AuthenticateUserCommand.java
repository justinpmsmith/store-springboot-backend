package com.allianceseeds.api.domain.commands.user;

import lombok.Getter;

@Getter
public class AuthenticateUserCommand implements UserCommand{
    private final String name;
    private final String passwordHash;

    public AuthenticateUserCommand(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }
}
