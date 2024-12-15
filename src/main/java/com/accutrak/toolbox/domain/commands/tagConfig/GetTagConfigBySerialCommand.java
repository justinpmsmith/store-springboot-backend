package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetTagConfigBySerialCommand implements TagConfigCommand{
    private String serial;


    public GetTagConfigBySerialCommand(String serial) {
       Validation.validateHex(serial);
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }


}
