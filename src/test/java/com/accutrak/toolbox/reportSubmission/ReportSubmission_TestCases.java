package com.accutrak.toolbox.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;

public class ReportSubmission_TestCases {

    public static final String validDeviceId = "someOtherDeviceId";

    public static final String inValidDeviceId = "";

    public static final String validScriptId = "ae0125ef";
    public static final String validUuid = "8a8e1213-e3cf-4be9-8833-2faed8597889";

    public static final int timestamp = 1716595200;

    public static final String siteWildcard = "*";

    public static final String siteDoringkop = "Doringkop";




    public static ReportSubmission validSubmission = ReportSubmission.builder().
            deviceId(validDeviceId).
            uuid(validUuid).
            scriptId(validScriptId).
            submissionTime(timestamp).
            response("Unit Test Response").
            site(siteWildcard).build();

    public static ReportSubmission inValidSubmission = ReportSubmission.builder().
            deviceId(inValidDeviceId).
            uuid(validUuid).
            scriptId(validScriptId).
            submissionTime(timestamp).
            response("Unit Test Response").
            site(siteWildcard).build();
}
