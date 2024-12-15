package com.accutrak.toolbox.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.domain.exceptions.InvalidDeviceIdException;
import com.accutrak.toolbox.presentation.resources.ReportSubmissionResource;
import static org.junit.jupiter.api.Assertions.*;

import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.reportSubmission.ReportSubmissionTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReportSubmission_Test {

    private ReportSubmissionResource submissionResource;

    @Autowired
    public ReportSubmission_Test(ReportSubmissionResource submissionResource) {
        this.submissionResource = submissionResource;
    }
    @Test
    void addValid_Test(){
        ReportSubmission submission = ReportSubmission_TestCases.validSubmission;
        Transformer result =  submissionResource.addReportSubmission(submission);

        assertTrue(((ReportSubmissionTransformer) result).getMeta().getSuccess());

    }
    @Test
    void addInValid_Test(){
        ReportSubmission submission = ReportSubmission_TestCases.inValidSubmission;

        assertThrows(InvalidDeviceIdException.class, ()-> submissionResource.addReportSubmission(submission));

    }

    @Test
    void getLastSubmissionTimestamp_validDeviceId_Test(){
        String deviceId = ReportSubmission_TestCases.validDeviceId;
        Transformer result =  submissionResource.getLastSubmissionTimestamp(deviceId);

        assertNotNull(result);
    }

    @Test
    void getLastSubmissionTimestamp_inValidDeviceId_Test(){
        String deviceId = ReportSubmission_TestCases.inValidDeviceId;

        assertThrows(InvalidDeviceIdException.class, ()->{submissionResource.getLastSubmissionTimestamp(deviceId);} );

    }

    @Test
    void getSubmissionsByDeviceId_validDeviceId_Test(){
        String deviceId = ReportSubmission_TestCases.validDeviceId;
        Transformer result =  submissionResource.getReportSubmissionsByDeviceId(deviceId);

        assertNotNull(result);
    }

    @Test
    void getSubmissionsByDeviceId_inValidDeviceId_Test(){
        String deviceId = ReportSubmission_TestCases.inValidDeviceId;

        assertThrows(InvalidDeviceIdException.class, ()->{submissionResource.getReportSubmissionsByDeviceId(deviceId);} );

    }
}
