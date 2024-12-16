package com.accutrak.toolbox.services.category;

import com.accutrak.toolbox.services.BaseTransformer;
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