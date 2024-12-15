package com.accutrak.toolbox.domain.commands.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import lombok.Getter;

@Getter
public class AddFirmwareCommand implements FirmwareCommand{
    private Firmware firmware;

    public AddFirmwareCommand(Firmware firmware) {
        firmware.site = firmware.site.toUpperCase();
        this.firmware = firmware;
    }
}
