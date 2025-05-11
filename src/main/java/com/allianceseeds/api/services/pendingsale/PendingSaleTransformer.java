package com.allianceseeds.api.services.pendingsale;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PendingSaleTransformer<T> extends BaseTransformer {
    public PendingSaleTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
}