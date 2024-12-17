package com.allianceseeds.api.adapters.repositories.product;

import com.allianceseeds.api.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaProductRepo extends JpaRepository<Product, Long> {
}
