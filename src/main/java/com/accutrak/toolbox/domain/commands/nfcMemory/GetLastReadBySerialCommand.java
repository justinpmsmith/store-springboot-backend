package com.accutrak.toolbox.domain.commands.nfcMemory;

import com.accutrak.toolbox.domain.validation.Validation;


public class GetLastReadBySerialCommand implements NfcMemoryCommand{

    private final String serial;

    public GetLastReadBySerialCommand(String serial) {
        Validation.validateHex(serial);
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }


}
