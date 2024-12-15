package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.validation.Validation;

public class GetLogsByDeviceIdCommand implements Command {
    String deviceId;

    public GetLogsByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
