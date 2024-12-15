package com.accutrak.toolbox.domain.commands.nfcMemory;

import com.accutrak.toolbox.domain.entities.NfcMemory;

public class AddNfcMemoryCommand implements NfcMemoryCommand {

    private final NfcMemory nfcMemory;

    public AddNfcMemoryCommand(NfcMemory nfcMemory) {
        this.nfcMemory = nfcMemory;
    }

    public NfcMemory getNfcMemory() {
        return nfcMemory;
    }
}
