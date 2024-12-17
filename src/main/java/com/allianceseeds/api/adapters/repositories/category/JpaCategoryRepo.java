package com.allianceseeds.api.adapters.repositories.category;

import com.allianceseeds.api.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaCategoryRepo extends JpaRepository<Category, Long> {
}
