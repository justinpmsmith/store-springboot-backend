package com.allianceseeds.api.services.customer;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTransformer<T>  extends BaseTransformer {

    public CustomerTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }


}
