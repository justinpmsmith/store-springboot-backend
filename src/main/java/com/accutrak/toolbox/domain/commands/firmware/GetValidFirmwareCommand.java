package com.accutrak.toolbox.domain.commands.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetValidFirmwareCommand implements FirmwareCommand{
    String site;

    public GetValidFirmwareCommand(String site) {
        this.site = site.toUpperCase();
    }

}
