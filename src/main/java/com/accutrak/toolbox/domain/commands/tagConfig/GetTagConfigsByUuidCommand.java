package com.accutrak.toolbox.domain.commands.tagConfig;

public class GetTagConfigsByUuidCommand implements TagConfigCommand{

    private final String uuid;

    public GetTagConfigsByUuidCommand(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
