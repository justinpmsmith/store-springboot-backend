package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.software.AddSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.DeleteSoftwareByNameCommand;
import com.accutrak.toolbox.domain.commands.software.GetLatestSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.GetSoftwareByInfoCommand;
import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.software.SoftwareTransformer;
import org.springframework.stereotype.Component;

@Component
public class SoftwareResource extends BaseResource{
    public SoftwareResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addSoftware(Software software){
        AddSoftwareCommand command = new AddSoftwareCommand(software);
        return messageBus.publishCommand(command);
    }

    public Transformer getSoftwareByFileName(String fileName){
        GetSoftwareByInfoCommand command = new GetSoftwareByInfoCommand(fileName);
        return messageBus.publishCommand(command);
    }


    public Transformer getLatestSoftwareInfo(String site){
        GetLatestSoftwareCommand command = new GetLatestSoftwareCommand(site);
        Transformer result = messageBus.publishCommand(command);

        ((SoftwareTransformer<?>) result).removeSoftwareBlob();

        return result;
    }


    public Transformer deleteFirmwareByFileName(String fileName){
        DeleteSoftwareByNameCommand command = new DeleteSoftwareByNameCommand(fileName);
        return messageBus.publishCommand(command);

    }

}
