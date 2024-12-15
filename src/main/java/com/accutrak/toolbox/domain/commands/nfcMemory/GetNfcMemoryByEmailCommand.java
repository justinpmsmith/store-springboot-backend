package com.accutrak.toolbox.domain.commands.nfcMemory;

public class GetNfcMemoryByEmailCommand implements NfcMemoryCommand{
    private String email;

    public GetNfcMemoryByEmailCommand(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
