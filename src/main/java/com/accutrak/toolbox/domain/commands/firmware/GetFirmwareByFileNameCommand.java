package com.accutrak.toolbox.domain.commands.firmware;

import lombok.Getter;

@Getter
public class GetFirmwareByFileNameCommand implements FirmwareCommand{
    String fileName;

    public GetFirmwareByFileNameCommand(String fileName) {
        this.fileName = fileName;
    }
}
