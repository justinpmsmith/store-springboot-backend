package com.accutrak.toolbox.services.tagConfig;


import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.services.BaseTransformer;

import java.util.List;

public class TagConfigTransformer<T> extends BaseTransformer {
    public TagConfigTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
    void setMostRecentLastChanged(List<TagConfig> tagConfigList) {
        int latest =  tagConfigList.stream()
                .mapToInt(TagConfig::getLastChanged)
                .max()
                .orElse(0); // Handle empty list (optional)
        setData(latest);
    }
}
