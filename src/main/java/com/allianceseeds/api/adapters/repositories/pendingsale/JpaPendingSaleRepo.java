package com.allianceseeds.api.adapters.repositories.pendingsale;

import com.allianceseeds.api.domain.entities.PendingSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface JpaPendingSaleRepo extends JpaRepository<PendingSale, Long> {
    Optional<PendingSale> findByPaymentId(String paymentId);
}