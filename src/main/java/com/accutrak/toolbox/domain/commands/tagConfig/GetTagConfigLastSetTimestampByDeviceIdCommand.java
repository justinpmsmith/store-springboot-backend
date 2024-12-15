package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetTagConfigLastSetTimestampByDeviceIdCommand implements TagConfigCommand{
    String deviceId;

    public GetTagConfigLastSetTimestampByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);

        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
