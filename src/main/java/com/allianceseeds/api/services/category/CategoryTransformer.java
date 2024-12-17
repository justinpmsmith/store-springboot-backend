package com.allianceseeds.api.services.category;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryTransformer<T> extends BaseTransformer {
    public CategoryTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
}