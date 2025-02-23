package com.allianceseeds.api.services.authentication;

import com.allianceseeds.api.services.BaseTransformer;

public class AuthTransformer <T> extends BaseTransformer {

    public AuthTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);

    }
}
