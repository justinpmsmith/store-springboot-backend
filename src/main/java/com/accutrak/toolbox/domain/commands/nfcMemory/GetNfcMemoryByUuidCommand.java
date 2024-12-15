package com.accutrak.toolbox.domain.commands.nfcMemory;

public class GetNfcMemoryByUuidCommand implements NfcMemoryCommand{

    private String uuid;

    public GetNfcMemoryByUuidCommand(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
