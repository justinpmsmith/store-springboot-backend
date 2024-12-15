package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.presentation.resources.ReportSubmissionResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReportSubmissionController {


    private ReportSubmissionResource submissionResource;

    @Autowired
    public ReportSubmissionController(ReportSubmissionResource submissionResource) {
        this.submissionResource = submissionResource;
    }

    @PostMapping("/client/addReportSubmission")
    public Transformer addSubmission(@RequestBody ReportSubmission submission){
        return submissionResource.addReportSubmission(submission);
    }

    @PostMapping("/client/bulkAddReportSubmission")
    public Transformer bulkAddSubmission(@RequestBody List<ReportSubmission> submissionList){
        return submissionResource.bulkAddReportSubmission(submissionList);
    }

    @GetMapping("/client/getLastSubmissionTimestamp")
    public Transformer getLastSubmissionTimestamp(@RequestParam String deviceId){
        return submissionResource.getLastSubmissionTimestamp(deviceId);
    }

    @GetMapping("/admin/getSubmissionsByDeviceId")
    public Transformer getSubmissionsByDeviceId(@RequestParam String deviceId){
        return submissionResource.getReportSubmissionsByDeviceId(deviceId);
    }
//    getReportSubmissionsInfoByDeviceId

    @GetMapping("/client/getSubmissionsInfoByDeviceId")
    public Transformer getSubmissionsInfoByDeviceId(@RequestParam String deviceId){
        return submissionResource.getReportSubmissionsInfoByDeviceId(deviceId);
    }
}
