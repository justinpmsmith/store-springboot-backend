package com.accutrak.toolbox.domain.commands.log;

import com.accutrak.toolbox.domain.entities.Log;

public class AddLogCommand implements LogCommand {
    private final Log log;

    public AddLogCommand(Log log) {
        this.log = log;
    }

    public Log getLog() {
        return log;
    }
}
