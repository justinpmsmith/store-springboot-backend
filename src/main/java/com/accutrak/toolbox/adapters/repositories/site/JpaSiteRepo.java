package com.accutrak.toolbox.adapters.repositories.site;

import com.accutrak.toolbox.domain.entities.Site;
import com.accutrak.toolbox.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaSiteRepo extends JpaRepository<Site, Long> {
}
