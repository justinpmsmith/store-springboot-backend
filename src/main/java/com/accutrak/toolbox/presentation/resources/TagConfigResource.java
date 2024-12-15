package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.tagConfig.*;
import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.tagConfig.TagConfigTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TagConfigResource extends BaseResource{

    @Autowired
    public TagConfigResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addTagConfig(TagConfig tagConfig){
        AddTagConfigCommand command = new AddTagConfigCommand(tagConfig);

        return messageBus.publishCommand(command);
    }

    public Transformer bulkAddTagConfig(List<TagConfig> tagConfigList){
        BulkAddTagCommand command = new BulkAddTagCommand(tagConfigList);
        return messageBus.publishCommand(command);

    }

    public Transformer deleteTagConfig(TagConfig tagConfig){
        DeleteTagConfigCommand command = new DeleteTagConfigCommand(tagConfig);

        return messageBus.publishCommand(command);
    }


    public Transformer getLastSet(String serial){
        GetLastSetBySerialCommand command = new GetLastSetBySerialCommand(serial);
        return messageBus.publishCommand(command);


    }

    public Transformer getTagConfigsByUuid(String uuid){
        GetTagConfigsByUuidCommand command = new GetTagConfigsByUuidCommand(uuid);
        return messageBus.publishCommand(command);


    }

    public Transformer getTagConfigsByDeviceId(String deviceId){
        GetTagConfigByDeviceIdCommand command = new GetTagConfigByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);


    }



    public Transformer getTagConfigsBySerial(String serial){
        GetTagConfigBySerialCommand command = new GetTagConfigBySerialCommand(serial);
        return messageBus.publishCommand(command);

    }

    public Transformer getTagConfigsByEmail(String email ){
        GetTagConfigsByEmailCommand command = new GetTagConfigsByEmailCommand(email);
        return messageBus.publishCommand(command);


    }

    public Transformer getAllTagConfig(){
        GetAllTagConfigsCommand command = new GetAllTagConfigsCommand();
        return messageBus.publishCommand(command);

    }

    public Transformer getTagConfigLastSetTimestampByUuid(String uuid){
        GetLatestTagConfigByUuidCommand command = new GetLatestTagConfigByUuidCommand(uuid);
        return messageBus.publishCommand(command);

    }

    public Transformer getTagConfigLastSetTimestampByDeviceId(String deviceId){
        GetTagConfigLastSetTimestampByDeviceIdCommand command = new GetTagConfigLastSetTimestampByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);

    }

}
