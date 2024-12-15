package com.accutrak.toolbox.services.reportScript;

import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportScriptTransformer<T> extends BaseTransformer {

    public ReportScriptTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public void removeScriptData(){
        List<ReportScript> scriptList = (List<ReportScript>) getData();
        scriptList.forEach(script -> {
            script.setScript(null);
            script.setTheme(null);
        });
        this.setData(scriptList);

    }
}
