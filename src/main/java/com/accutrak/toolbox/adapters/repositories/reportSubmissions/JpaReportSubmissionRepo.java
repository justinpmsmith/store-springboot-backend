package com.accutrak.toolbox.adapters.repositories.reportSubmissions;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaReportSubmissionRepo extends JpaRepository<ReportSubmission, Long> {
}
