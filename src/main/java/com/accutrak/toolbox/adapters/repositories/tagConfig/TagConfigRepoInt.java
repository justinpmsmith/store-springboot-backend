package com.accutrak.toolbox.adapters.repositories.tagConfig;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.TagConfig;

import java.util.List;

public interface TagConfigRepoInt extends RepositoryInt<TagConfig> {
    TagConfig getLastTagSetBySerial(String serial);

    List<TagConfig> getTagConfigsByUsuerId(String uuid);

    List<TagConfig> getTagConfigsByStringField(String field, String value);

    List<TagConfig> getAll();

}
