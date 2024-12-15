package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.presentation.resources.ReportScriptResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReportScriptController {

    private ReportScriptResource rsResource;

    @Autowired
    public ReportScriptController(ReportScriptResource rsResource) {
        this.rsResource = rsResource;
    }


    @PostMapping("/admin/addReportScript")
    public Transformer addFirmware(@RequestBody ReportScript script){
        return rsResource.addReportScript(script);
    }

    @DeleteMapping("/admin/deleteReportScriptByScriptId")
    public Transformer deleteReportScriptByScriptId(@RequestParam String scriptId){
        return rsResource.deleteReportScriptByScriptId(scriptId);

    }

    @GetMapping("/client/getValidReportScriptsInfoBySite")
    public Transformer getValidReportScriptsInfoBySite(@RequestParam String site){
        return rsResource.getValidScriptsInfo(site);

    }
    @GetMapping("/client/getValidReportScriptsBySite")
    public Transformer getValidReportScriptsBySite(@RequestParam String site){
        return rsResource.getValidScripts(site);

    }
    @GetMapping("/client/getReportScriptByScriptId")
    public Transformer getReportScriptByScriptId(@RequestParam String scriptId){
        return rsResource.getScriptByScriptId(scriptId);

    }
}
