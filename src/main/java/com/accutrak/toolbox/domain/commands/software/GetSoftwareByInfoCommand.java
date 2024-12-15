package com.accutrak.toolbox.domain.commands.software;

public class GetSoftwareByInfoCommand implements SoftwareCommand{
    String fileName;

    public GetSoftwareByInfoCommand(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
