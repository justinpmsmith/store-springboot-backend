package com.accutrak.toolbox.domain.commands.reportScript;

import lombok.Getter;

@Getter
public class DeleteScriptByScriptIdCommand implements ReportScriptCommand{
    String scriptId;

    public DeleteScriptByScriptIdCommand(String scriptId) {
        this.scriptId = scriptId;
    }
}
