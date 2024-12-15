package com.accutrak.toolbox.adapters.repositories.otp;

import com.accutrak.toolbox.domain.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaOtpRepo extends JpaRepository<Otp, String> {
}
