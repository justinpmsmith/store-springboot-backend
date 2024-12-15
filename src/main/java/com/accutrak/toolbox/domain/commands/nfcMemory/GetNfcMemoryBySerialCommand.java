package com.accutrak.toolbox.domain.commands.nfcMemory;


import com.accutrak.toolbox.domain.validation.Validation;

public class GetNfcMemoryBySerialCommand implements NfcMemoryCommand{
    String serial;

    public GetNfcMemoryBySerialCommand(String serial) {
        Validation.validateHex(serial);
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }


}
