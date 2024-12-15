package com.accutrak.toolbox.reportScript;

import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.domain.exceptions.InvalidHexException;
import com.accutrak.toolbox.presentation.resources.ReportScriptResource;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.reportScript.ReportScriptTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReportScript_Test {
    private ReportScriptResource rsResource;

    @Autowired
    public ReportScript_Test(ReportScriptResource rsResource) {
        this.rsResource = rsResource;
    }

    // valid
    @Test
    void addDeleteScript_Test(){
        Transformer addResult = rsResource.addReportScript(ReportScript_TestCases.reportScriptValid);
        Transformer deleteResult = rsResource.deleteReportScriptByScriptId(ReportScript_TestCases.validScriptId);
        assertEquals(((ReportScriptTransformer<?>) addResult).getMeta().getSuccess(), true);
        assertEquals(((ReportScriptTransformer<?>) deleteResult).getMeta().getSuccess(), true);
    }

    @Test
    void getValidScript_AllSites_Test(){
        rsResource.addReportScript(ReportScript_TestCases.reportScriptValid);
        List<ReportScript> scriptList = (List<ReportScript>) ((ReportScriptTransformer<?>) rsResource.getValidScriptsInfo("*")).getData();
        rsResource.deleteReportScriptByScriptId(ReportScript_TestCases.validScriptId);

        assertNotNull(scriptList.size());
    }

//    @Test
//    void getScriptById_Test(){
//        rsResource.addReportScript(ReportScript_TestCases.reportScriptValid);
////        Thread.sleep
//        ReportScript script = (ReportScript) ((ReportScriptTransformer<?>) rsResource.getScriptByScriptId(ReportScript_TestCases.validScriptId)).getData();
//        rsResource.deleteReportScriptByScriptId(ReportScript_TestCases.validScriptId);
//
//
//        assertNotNull(script);
//    }
//

    // invalid
    @Test
    void getScriptById_InvalidId_Test(){

        assertThrows(InvalidHexException.class,
                ()->{ReportScript script = (ReportScript) ((ReportScriptTransformer<?>) rsResource.getScriptByScriptId(ReportScript_TestCases.inValidScriptId)).getData();});
    }


    // doringkop

    @Test
    void getValidScript_Doringkop_Test(){
        rsResource.addReportScript(ReportScript_TestCases.reportScriptDoringKop);
        List<ReportScript> scriptList = (List<ReportScript>) ((ReportScriptTransformer<?>) rsResource.getValidScriptsInfo(ReportScript_TestCases.siteDoringKop)).getData();
        rsResource.deleteReportScriptByScriptId(ReportScript_TestCases.validScriptId);

        assertNotNull(scriptList.size());
    }

}
