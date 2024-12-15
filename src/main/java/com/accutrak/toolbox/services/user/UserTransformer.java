package com.accutrak.toolbox.services.user;

import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserTransformer<T> extends BaseTransformer {
    public UserTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
}
