package com.allianceseeds.api.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTransformer<T> implements Transformer {
    protected Meta meta;
    public T data;

    public BaseTransformer() {
        this.meta = new Meta();

    }

    public void setMessage(String message){
        this.meta.setMessage(message);
    }

    public void setSuccess(Boolean success) {
        this.meta.setSuccess(success);

        if(success){
            this.meta.setMessage("OK");
        }

    }

}
