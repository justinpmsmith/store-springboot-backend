package com.accutrak.toolbox.services.software;

import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftwareTransformer<T> extends BaseTransformer {
    public SoftwareTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public void removeSoftwareBlob(){
        Software software = (Software) getData();

        if(software != null){
            software.setBlob(null);
            setData(software);
        }

    }
}
