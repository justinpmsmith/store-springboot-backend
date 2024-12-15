package com.accutrak.toolbox.domain.commands.nfcMemory;

import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.NfcMemory;

import java.util.List;

public class BulkAddNfcMemoryCommand implements NfcMemoryCommand{
    List<NfcMemory> nfcMemoryList;

    public BulkAddNfcMemoryCommand(List<NfcMemory> nfcMemoryList) {
        this.nfcMemoryList = nfcMemoryList;
    }

    public List<NfcMemory> getNfcMemoryList() {
        return nfcMemoryList;
    }
}
