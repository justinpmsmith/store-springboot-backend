package com.accutrak.toolbox.services.site;

import com.accutrak.toolbox.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class SiteTransformer<T> extends BaseTransformer {
    public SiteTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);

    }


}


