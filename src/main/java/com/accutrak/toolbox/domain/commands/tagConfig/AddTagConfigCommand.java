package com.accutrak.toolbox.domain.commands.tagConfig;

import com.accutrak.toolbox.domain.entities.TagConfig;

public class AddTagConfigCommand implements TagConfigCommand{
    private final TagConfig tagConfig;

    public AddTagConfigCommand(TagConfig tagConfig) {
        // Custom validator in
        this.tagConfig = tagConfig;
    }

    public TagConfig getTagConfig() {
        return tagConfig;
    }
}
