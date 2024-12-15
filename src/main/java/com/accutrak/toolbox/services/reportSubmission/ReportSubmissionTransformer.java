package com.accutrak.toolbox.services.reportSubmission;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportSubmissionTransformer<T> extends BaseTransformer {
    public ReportSubmissionTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public void removeSubmissionData(){
        List<ReportSubmission> submissionList = ((List<ReportSubmission>) getData());
        submissionList.forEach(submission -> submission.setResponse(null));
        setData(submissionList);

    }
}
