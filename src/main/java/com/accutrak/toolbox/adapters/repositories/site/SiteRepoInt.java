package com.accutrak.toolbox.adapters.repositories.site;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Site;

import java.util.List;

public interface SiteRepoInt extends RepositoryInt<Site> {
    List<Site> getAll();
}
