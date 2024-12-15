package com.accutrak.toolbox.services.device;

import com.accutrak.toolbox.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceTransformer <T> extends BaseTransformer {

    public DeviceTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);

    }
}
