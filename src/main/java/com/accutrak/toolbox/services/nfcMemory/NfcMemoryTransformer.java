package com.accutrak.toolbox.services.nfcMemory;

import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;

import java.util.List;
import java.util.Map;

public class NfcMemoryTransformer<T> extends BaseTransformer  {
    public NfcMemoryTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }
}
