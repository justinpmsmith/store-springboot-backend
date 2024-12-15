package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.reportScript.AddReportScriptCommand;
import com.accutrak.toolbox.domain.commands.reportScript.DeleteScriptByScriptIdCommand;
import com.accutrak.toolbox.domain.commands.reportScript.GetScriptByScriptIdCommand;
import com.accutrak.toolbox.domain.commands.reportScript.GetValidScriptsBySiteCommand;
import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.reportScript.ReportScriptTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportScriptResource extends BaseResource{
    public ReportScriptResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addReportScript(ReportScript script){
        AddReportScriptCommand command = new AddReportScriptCommand(script);
        return messageBus.publishCommand(command);

    }

    public Transformer deleteReportScriptByScriptId(String scriptId){
        DeleteScriptByScriptIdCommand command = new DeleteScriptByScriptIdCommand(scriptId);
        return messageBus.publishCommand(command);
    }

    public Transformer getValidScriptsInfo(String site){
        GetValidScriptsBySiteCommand command = new GetValidScriptsBySiteCommand(site);
        Transformer result = messageBus.publishCommand(command);

        ((ReportScriptTransformer<?>) result).removeScriptData();

        return result;
    }
    public Transformer getValidScripts(String site){
        GetValidScriptsBySiteCommand command = new GetValidScriptsBySiteCommand(site);

        return messageBus.publishCommand(command);
    }

    public Transformer getScriptByScriptId(String scriptId){
        GetScriptByScriptIdCommand command = new GetScriptByScriptIdCommand(scriptId);
        return messageBus.publishCommand(command);
    }
}
