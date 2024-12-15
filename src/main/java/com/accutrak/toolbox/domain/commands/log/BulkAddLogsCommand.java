package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.entities.Log;

import java.util.List;

public class BulkAddLogsCommand implements LogCommand{
    List<Log> logList;

    public BulkAddLogsCommand(List<Log> logList) {
        this.logList = logList;
    }

    public List<Log> getLogList() {
        return logList;
    }
}
