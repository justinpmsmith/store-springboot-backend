package com.accutrak.toolbox.domain.commands.tagConfig;


import com.accutrak.toolbox.domain.validation.Validation;

public class GetLastSetBySerialCommand implements TagConfigCommand{

    private final String serial;

    public GetLastSetBySerialCommand(String serial) {
        Validation.validateHex(serial);
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }


}
