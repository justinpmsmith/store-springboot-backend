package com.accutrak.toolbox.adapters.repositories.software;

import com.accutrak.toolbox.domain.entities.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaSoftwareRepo extends JpaRepository<Software, Long> {
}
