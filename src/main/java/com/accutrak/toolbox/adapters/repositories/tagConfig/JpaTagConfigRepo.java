package com.accutrak.toolbox.adapters.repositories.tagConfig;

import com.accutrak.toolbox.domain.entities.TagConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaTagConfigRepo extends JpaRepository<TagConfig, Long>{

}






