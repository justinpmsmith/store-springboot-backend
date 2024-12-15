package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.validation.Validation;

import java.util.List;

public class GetLastModifiedTimestampByDeviceIdCommand implements LogCommand{

    String deviceId;

    public GetLastModifiedTimestampByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int findLatestModifiedTs(List<Log> logList) {
        return logList.stream()
                .mapToInt(Log::getLastModified)
                .max()
                .orElse(0); // Handle empty list
    }
}
