package com.allianceseeds.api.adapters.repositories.soldProducts;

import com.allianceseeds.api.domain.entities.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaSoldProductRepo extends JpaRepository<SoldProduct, Long> {
}