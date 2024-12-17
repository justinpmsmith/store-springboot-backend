package com.allianceseeds.api.services.product;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductTransformer<T> extends BaseTransformer {
    public ProductTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
}
