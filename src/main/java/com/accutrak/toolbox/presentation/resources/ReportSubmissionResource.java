package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.log.GetLogsByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.AddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.BulkAddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetLastSubmissionTimestampByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetReportSubmissionsByDeviceIdCommand;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.firmware.FirmwareTransformer;
import com.accutrak.toolbox.services.log.LogTransformer;
import com.accutrak.toolbox.services.reportSubmission.ReportSubmissionHandler;
import com.accutrak.toolbox.services.reportSubmission.ReportSubmissionTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportSubmissionResource extends BaseResource{
    public ReportSubmissionResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addReportSubmission(ReportSubmission submission){
        AddReportSubmissionCommand command = new AddReportSubmissionCommand(submission);
        return messageBus.publishCommand(command);
    }

    public Transformer bulkAddReportSubmission(List<ReportSubmission> submissionList){
        BulkAddReportSubmissionCommand command = new BulkAddReportSubmissionCommand(submissionList);
        return messageBus.publishCommand(command);
    }

    public Transformer getLastSubmissionTimestamp(String deviceId){
        GetLastSubmissionTimestampByDeviceIdCommand command = new GetLastSubmissionTimestampByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);
    }

    public Transformer getReportSubmissionsByDeviceId(String deviceId){
        GetReportSubmissionsByDeviceIdCommand command = new GetReportSubmissionsByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);

    }

    public Transformer getReportSubmissionsInfoByDeviceId(String deviceId){
        GetReportSubmissionsByDeviceIdCommand command = new GetReportSubmissionsByDeviceIdCommand(deviceId);
        Transformer result = messageBus.publishCommand(command);
        ((ReportSubmissionTransformer<?>) result).removeSubmissionData();
        return result;

    }

}
