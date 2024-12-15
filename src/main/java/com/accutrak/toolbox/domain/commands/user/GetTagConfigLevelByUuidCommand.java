package com.accutrak.toolbox.domain.commands.user;

import lombok.Getter;

@Getter
public class GetTagConfigLevelByUuidCommand implements UserCommand{

    String uuid;

    public GetTagConfigLevelByUuidCommand(String uuid) {
        this.uuid = uuid;
    }
}
