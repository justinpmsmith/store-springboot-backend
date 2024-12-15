package com.accutrak.toolbox.domain.commands.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

import java.util.List;

@Getter
public class GetLastSubmissionTimestampByDeviceIdCommand implements ReportSubmissionCommand{
    String deviceId;

    public GetLastSubmissionTimestampByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
    }
    public int findLatestSubmission(List<ReportSubmission> submissionList) {
        return submissionList.stream()
                .mapToInt(ReportSubmission::getSubmissionTime)
                .max()
                .orElse(0); // Handle empty list (optional)
    }
}
