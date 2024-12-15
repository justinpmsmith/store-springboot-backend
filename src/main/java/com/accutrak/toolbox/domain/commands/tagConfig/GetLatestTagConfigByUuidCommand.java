package com.accutrak.toolbox.domain.commands.tagConfig;

public class GetLatestTagConfigByUuidCommand implements TagConfigCommand{
    String uuid;

    public GetLatestTagConfigByUuidCommand(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
