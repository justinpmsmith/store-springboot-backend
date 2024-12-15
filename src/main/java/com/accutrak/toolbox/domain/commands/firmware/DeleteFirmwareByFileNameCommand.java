package com.accutrak.toolbox.domain.commands.firmware;

import lombok.Getter;

@Getter
public class DeleteFirmwareByFileNameCommand implements FirmwareCommand{
    String fileName;

    public DeleteFirmwareByFileNameCommand(String fileName) {
        this.fileName = fileName;
    }
}
