package com.accutrak.toolbox.services.reportSubmission;

import com.accutrak.toolbox.adapters.repositories.reportSubmissions.ReportSubmissionRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.reportSubmission.AddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.BulkAddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetLastSubmissionTimestampByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetReportSubmissionsByDeviceIdCommand;
import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportSubmissionHandler {

    private UnitOfWork reportSubmissionUOW;

    ReportSubmissionRepo submissionRepo;

    @Autowired
    public ReportSubmissionHandler(ReportSubmissionRepo submissionRepo) {
        this.submissionRepo = submissionRepo;
        this. reportSubmissionUOW = new UnitOfWork<ReportSubmission>(submissionRepo);
    }

    public Transformer addSubmission(Command command){
        ReportSubmission reportSubmission = ((AddReportSubmissionCommand) command).getReportSubmission();
        reportSubmissionUOW.registerRepoOperation(reportSubmission, UnitOfWorkInt.UnitActions.INSERT);
        reportSubmissionUOW.commit();

        return new ReportSubmissionTransformer<>(true, null);
    }
    public Transformer bulkAddSubmission(Command command){
        List<ReportSubmission> reportSubmissionList = ((BulkAddReportSubmissionCommand) command).getSubmissionList();
        reportSubmissionUOW.registerBulkOperation(reportSubmissionList, UnitOfWorkInt.UnitActions.INSERT);
        reportSubmissionUOW.commit();

        return new ReportSubmissionTransformer<>(true, null);
    }

    public Transformer getSubmissionsByDeviceId(Command command){
        String deviceId = ((GetReportSubmissionsByDeviceIdCommand) command).getDeviceId();
        List<ReportSubmission> submissionList = submissionRepo.getReportSubmissionsByStringField("deviceId", deviceId);
        return new ReportSubmissionTransformer<>(true, submissionList);
    }

    public Transformer getLastSubmissionTimestampByDeviceId(Command command){
        String deviceId = ((GetLastSubmissionTimestampByDeviceIdCommand) command).getDeviceId();
        List<ReportSubmission> submissionList = submissionRepo.getReportSubmissionsByStringField("deviceId", deviceId);
        int lastSubmission = ((GetLastSubmissionTimestampByDeviceIdCommand) command).findLatestSubmission(submissionList);

        return new ReportSubmissionTransformer<>(true, lastSubmission);
    }
}
