package com.accutrak.toolbox.domain.commands.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import lombok.Getter;

import java.util.List;

@Getter
public class BulkAddReportSubmissionCommand implements ReportSubmissionCommand{
    List<ReportSubmission> submissionList;

    public BulkAddReportSubmissionCommand(List<ReportSubmission> submissionList) {
        this.submissionList = submissionList;
    }
}
