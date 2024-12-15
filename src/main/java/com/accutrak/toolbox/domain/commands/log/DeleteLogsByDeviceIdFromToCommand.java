package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class DeleteLogsByDeviceIdFromToCommand implements LogCommand{

    String deviceId;
    int from;
    int to;

    public DeleteLogsByDeviceIdFromToCommand(String deviceId, int from, int to) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
        this.from = from;
        this.to = to;
    }
}
