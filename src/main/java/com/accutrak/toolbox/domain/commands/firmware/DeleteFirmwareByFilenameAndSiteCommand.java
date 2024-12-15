package com.accutrak.toolbox.domain.commands.firmware;

import lombok.Getter;

@Getter
public class DeleteFirmwareByFilenameAndSiteCommand implements FirmwareCommand{
    String filename;

    String site;

    public DeleteFirmwareByFilenameAndSiteCommand(String filename, String site) {
        this.filename = filename;
        this.site = site.toUpperCase();
    }
}
