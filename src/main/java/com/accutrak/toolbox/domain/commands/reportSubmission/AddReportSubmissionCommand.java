package com.accutrak.toolbox.domain.commands.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class AddReportSubmissionCommand implements ReportSubmissionCommand{
    ReportSubmission reportSubmission;

    public AddReportSubmissionCommand(ReportSubmission reportSubmission) {
        Validation.validateDeviceId(reportSubmission.getDeviceId());
        this.reportSubmission = reportSubmission;
    }
}
