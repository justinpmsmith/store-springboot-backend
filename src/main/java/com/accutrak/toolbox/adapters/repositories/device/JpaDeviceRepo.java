package com.accutrak.toolbox.adapters.repositories.device;

import com.accutrak.toolbox.domain.entities.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JpaDeviceRepo extends JpaRepository<Device, String> {

}
