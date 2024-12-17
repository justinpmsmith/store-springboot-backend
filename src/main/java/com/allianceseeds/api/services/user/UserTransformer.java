package com.allianceseeds.api.services.user;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTransformer<T> extends BaseTransformer {
    public UserTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public UserTransformer(Boolean success, T data, String message) {
        super();
        setSuccess(success);
        this.setData(data);
        this.setMessage(message);
    }
}
