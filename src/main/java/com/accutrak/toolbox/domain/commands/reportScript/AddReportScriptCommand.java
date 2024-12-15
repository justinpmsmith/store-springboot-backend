package com.accutrak.toolbox.domain.commands.reportScript;


import com.accutrak.toolbox.domain.entities.ReportScript;
import lombok.Getter;

@Getter
public class AddReportScriptCommand implements ReportScriptCommand{
    ReportScript reportScript;

    public AddReportScriptCommand(ReportScript reportScript) {
        this.reportScript = reportScript;
    }
}
