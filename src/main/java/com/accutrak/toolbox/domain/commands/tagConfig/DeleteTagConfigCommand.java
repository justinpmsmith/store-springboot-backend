package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.entities.TagConfig;
import lombok.Getter;

@Getter
public class DeleteTagConfigCommand implements TagConfigCommand{
    TagConfig tagConfig;

    public DeleteTagConfigCommand(TagConfig tagConfig) {
        this.tagConfig = tagConfig;
    }
}
