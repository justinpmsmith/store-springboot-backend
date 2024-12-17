package com.allianceseeds.api.adapters.repositories.user;

import com.allianceseeds.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaUserRepo extends JpaRepository<User, Long> {
}