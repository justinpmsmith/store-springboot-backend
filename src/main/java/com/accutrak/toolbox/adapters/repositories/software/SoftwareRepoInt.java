package com.accutrak.toolbox.adapters.repositories.software;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Software;

import java.util.List;

public interface SoftwareRepoInt extends RepositoryInt<Software> {
    List<Software> getValidSoftware();

}
