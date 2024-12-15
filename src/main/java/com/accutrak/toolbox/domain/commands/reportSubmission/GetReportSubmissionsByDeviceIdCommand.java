package com.accutrak.toolbox.domain.commands.reportSubmission;

import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class GetReportSubmissionsByDeviceIdCommand implements ReportSubmissionCommand{
    String deviceId;

    public GetReportSubmissionsByDeviceIdCommand(String deviceId) {
        Validation.validateDeviceId(deviceId);
        this.deviceId = deviceId;
    }
}
