package com.accutrak.toolbox.domain.commands.nfcMemory;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetNfcMemoryLastReadByDeviceIdCommand implements NfcMemoryCommand{
    String deviceId;

    public GetNfcMemoryLastReadByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
