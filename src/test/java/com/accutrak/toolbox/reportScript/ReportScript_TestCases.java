package com.accutrak.toolbox.reportScript;

import com.accutrak.toolbox.domain.entities.ReportScript;

public class ReportScript_TestCases {

    public static String alias = "unit test";

    public static String revision = "unit test rev";

    public static String siteWildCard = "*";

    public static String siteDoringKop = "*";


    public static int from = 1716595200;

    public static int to = 1843123423;

    public static String script = "Test script";
    public static String theme = "Test theme";

    public static String validScriptId = "25efae01";

    public static String inValidScriptId = "invalid id";



    public static ReportScript reportScriptValid = ReportScript.builder().
            alias(alias).
            version(revision).
            effectiveFrom(from).
            effectiveTo(to).site("*").
            script(script).
            theme(theme).
            scriptId(validScriptId).
            build();

    public static ReportScript reportScriptInValid = ReportScript.builder().
            alias(alias).
            version(revision).
            effectiveFrom(from).
            effectiveTo(to).site("*").
            script(script).
            theme(theme).
            scriptId(inValidScriptId).
            build();

    public static ReportScript reportScriptDoringKop = ReportScript.builder().
            alias(alias).
            version(revision).
            effectiveFrom(from).
            effectiveTo(to).site(siteDoringKop).
            script(script).
            theme(theme).
            scriptId(validScriptId).
            build();

}
