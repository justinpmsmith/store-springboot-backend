package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.entities.TagConfig;

import java.util.List;

public class BulkAddTagCommand implements TagConfigCommand{

    List<TagConfig> tagConfigList;

    public BulkAddTagCommand(List<TagConfig> tagConfigList) {
        this.tagConfigList = tagConfigList;
    }

    public List<TagConfig> getTagConfigList() {
        return tagConfigList;
    }
}
