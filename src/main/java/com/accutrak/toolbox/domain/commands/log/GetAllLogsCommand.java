package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.entities.Log;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetAllLogsCommand implements LogCommand{
    public Map<String, List<Log>> groupLogsByDeviceId(List<Log> logList) {
        return logList.stream()
                .collect(Collectors.groupingBy(Log::getDeviceId));
    }

}
