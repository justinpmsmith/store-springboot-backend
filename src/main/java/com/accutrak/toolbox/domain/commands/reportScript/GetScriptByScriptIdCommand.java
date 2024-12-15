package com.accutrak.toolbox.domain.commands.reportScript;

import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class GetScriptByScriptIdCommand implements ReportScriptCommand{
    String scriptId;

    public GetScriptByScriptIdCommand(String scriptId) {
        Validation.validateHex(scriptId);
        this.scriptId = scriptId;
    }
}
