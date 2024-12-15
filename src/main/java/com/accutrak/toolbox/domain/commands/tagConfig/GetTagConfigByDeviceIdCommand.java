package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetTagConfigByDeviceIdCommand implements TagConfigCommand{
    String deviceId;

    public GetTagConfigByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);

        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
