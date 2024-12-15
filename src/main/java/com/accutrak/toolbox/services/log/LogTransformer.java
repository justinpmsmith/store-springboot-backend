package com.accutrak.toolbox.services.log;

import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;

import java.util.List;
import java.util.Map;

public class LogTransformer<T> extends BaseTransformer {
    public LogTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public void removeLogData(){
        List<Log> logList = (List<Log>) getData();
        logList.forEach(log -> log.setData(null));
        setData(logList);
    }
}
