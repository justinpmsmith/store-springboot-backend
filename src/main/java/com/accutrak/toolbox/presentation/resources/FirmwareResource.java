package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.firmware.*;
import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.firmware.FirmwareTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirmwareResource extends BaseResource{
    public FirmwareResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addFirmware(Firmware firmware){
        AddFirmwareCommand command = new AddFirmwareCommand(firmware);
        return messageBus.publishCommand(command);

    }

    public Transformer deleteFirmwareByFileName(String fileName){
        DeleteFirmwareByFileNameCommand command = new DeleteFirmwareByFileNameCommand(fileName);
        return messageBus.publishCommand(command);
    }

    public Transformer deleteFirmwareByFileNameAndSite(String fileName, String site){
        DeleteFirmwareByFilenameAndSiteCommand command = new DeleteFirmwareByFilenameAndSiteCommand(fileName, site);
        return messageBus.publishCommand(command);
    }


    public Transformer getValidFirmwareInfo(String site){
        GetValidFirmwareCommand command = new GetValidFirmwareCommand(site);
        Transformer result = messageBus.publishCommand(command);

        ((FirmwareTransformer) result).removeBlob();
//        firmwareList.forEach(firmware -> firmware.setBlob(null));
        return result;

    }

    public Transformer getValidFirmware(String site){
        GetValidFirmwareCommand command = new GetValidFirmwareCommand(site);
        Transformer result = messageBus.publishCommand(command);

        return result;

    }

    public Transformer getAllFirmware(){
        GetAllFirmwareCommand command = new GetAllFirmwareCommand();
        Transformer result = messageBus.publishCommand(command);
        return result;
    }
    public Transformer getAllFirmwareInfo(){
        GetAllFirmwareCommand command = new GetAllFirmwareCommand();
        Transformer result = messageBus.publishCommand(command);
        ((FirmwareTransformer) result).removeBlob();

        return result;
    }
    public Transformer getFirmwareByFileName(String fileName){
        GetFirmwareByFileNameCommand command = new GetFirmwareByFileNameCommand(fileName);
        Transformer result = messageBus.publishCommand(command);
        return result;

    }

}
