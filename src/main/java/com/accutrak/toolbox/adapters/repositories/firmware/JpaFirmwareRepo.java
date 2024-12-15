package com.accutrak.toolbox.adapters.repositories.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaFirmwareRepo extends JpaRepository<Firmware, Long> {
}
