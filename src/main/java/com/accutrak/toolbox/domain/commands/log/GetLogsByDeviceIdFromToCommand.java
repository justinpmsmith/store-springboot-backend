package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.validation.Validation;

public class GetLogsByDeviceIdFromToCommand implements LogCommand{
    String deviceId;
    private  int to;
    private int from;

    public GetLogsByDeviceIdFromToCommand(String deviceId, int from, int to) {
        Validation.validateDeviceId(deviceId);

        this.deviceId = deviceId;
        this.to = to;
        this.from = from;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }
}
