package com.accutrak.toolbox.services.reportScript;

import com.accutrak.toolbox.adapters.repositories.reportScript.ReportScriptRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.reportScript.*;
import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportScriptHandler {

    private UnitOfWork reportScriptUOW;

    private ReportScriptRepo rsRepo;

    public ReportScriptHandler(ReportScriptRepo rsRepo) {
        this.rsRepo = rsRepo;
        this.reportScriptUOW = new UnitOfWork<ReportScript>(rsRepo);
    }

    public Transformer addReportScript(Command command) {
        ReportScript script = ((AddReportScriptCommand) command).getReportScript();

        try {
            reportScriptUOW.registerRepoOperation(script, UnitOfWorkInt.UnitActions.INSERT);
            reportScriptUOW.commit();
            return new ReportScriptTransformer<>(true, null);

        } catch (DataIntegrityViolationException e) { // Catch JPA exception for uniqueness constraint
            return new ReportScriptTransformer<>(false, null);
        }


    }


    public Transformer getValidScriptsInfo(Command command){
        String site = ((GetValidScriptsBySiteCommand) command).getSite();
        List<ReportScript> scriptList = rsRepo.getValidScripts();
        List<ReportScript> filteredScriptList = ((GetValidScriptsBySiteCommand) command).filterBySite(scriptList, site);

        return new ReportScriptTransformer<>(true, filteredScriptList);

    }

    public Transformer getScriptByScriptId(Command command) {
        String scriptId = ((GetScriptByScriptIdCommand) command).getScriptId();

        try {
            ReportScript script = rsRepo.getSingleScriptByStringField("scriptId", scriptId);
            return new ReportScriptTransformer<>(true, script);
        } catch (EmptyResultDataAccessException e) {
            // Likely NoResultException (underlying cause)
            return new ReportScriptTransformer<>(false, null); // Set script to null on exception
        }

    }


    public Transformer deleteScriptByScriptId(Command command) {
        String scriptId = ((DeleteScriptByScriptIdCommand) command).getScriptId();


        try {
            ReportScript script = rsRepo.getSingleScriptByStringField("scriptId", scriptId);
            if (script != null) {
                reportScriptUOW.registerRepoOperation(script, UnitOfWorkInt.UnitActions.DELETE);
                reportScriptUOW.commit();
                return new ReportScriptTransformer<>(true, null);
            } else {
                return new ReportScriptTransformer<>(false, null);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ReportScriptTransformer<>(false, null);

        }

    }
    
}
