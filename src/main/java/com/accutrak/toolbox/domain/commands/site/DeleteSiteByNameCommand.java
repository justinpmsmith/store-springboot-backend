package com.accutrak.toolbox.domain.commands.site;

import lombok.Getter;

@Getter
public class DeleteSiteByNameCommand implements SiteCommand{
    String name;

    public DeleteSiteByNameCommand(String name) {
        this.name = name.toUpperCase();
    }
}
