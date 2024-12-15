package com.accutrak.toolbox.adapters.repositories.log;

import com.accutrak.toolbox.domain.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaLogRepo extends JpaRepository<Log, Long> {
}
