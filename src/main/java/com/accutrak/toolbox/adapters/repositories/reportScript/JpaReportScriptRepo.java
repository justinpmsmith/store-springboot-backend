package com.accutrak.toolbox.adapters.repositories.reportScript;

import com.accutrak.toolbox.domain.entities.ReportScript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaReportScriptRepo extends JpaRepository<ReportScript, Long> {
}
