package com.accutrak.toolbox.domain.commands.software;

import lombok.Getter;

@Getter
public class DeleteSoftwareByNameCommand implements SoftwareCommand{
    String fileName;

    public DeleteSoftwareByNameCommand(String fileName) {
        this.fileName = fileName;
    }
}
