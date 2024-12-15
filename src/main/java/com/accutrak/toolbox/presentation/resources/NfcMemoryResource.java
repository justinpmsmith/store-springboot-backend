package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.nfcMemory.*;
import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.nfcMemory.NfcMemoryTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NfcMemoryResource extends BaseResource{
    public NfcMemoryResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addNfcMemory(NfcMemory nfcMemory){
        AddNfcMemoryCommand command = new AddNfcMemoryCommand(nfcMemory);

        return messageBus.publishCommand(command);

    }

    public Transformer bulkAddNfcMemory(List<NfcMemory> nfcMemoryList){
        BulkAddNfcMemoryCommand command = new BulkAddNfcMemoryCommand(nfcMemoryList);

        return messageBus.publishCommand(command);
    }

    public Transformer getLastReadBySerial(String serial){
        GetLastReadBySerialCommand command = new GetLastReadBySerialCommand(serial);
        return messageBus.publishCommand(command);
    }

    public Transformer getLastReadByDeviceId(String deviceId){
        GetNfcMemoryLastReadByDeviceIdCommand command = new GetNfcMemoryLastReadByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);

    }


    public Transformer getNfcMemoryByUuid(String uuid){
        GetNfcMemoryByUuidCommand command = new GetNfcMemoryByUuidCommand(uuid);
        return messageBus.publishCommand(command);

    }

    public Transformer getNfcMemoryBySerial(String serial){
        GetNfcMemoryBySerialCommand command = new GetNfcMemoryBySerialCommand(serial);
        Transformer result = messageBus.publishCommand(command);
        return result;
    }

    public Transformer getNfcMemoryByEmail(String email){
        GetNfcMemoryByEmailCommand command = new GetNfcMemoryByEmailCommand(email);
        return messageBus.publishCommand(command);
    }

    public Transformer getNfcMemoryByDeviceId(String deviceId){
        GetNfcMemoryByDeviceIdCommand command = new GetNfcMemoryByDeviceIdCommand(deviceId);
        Transformer result = messageBus.publishCommand(command);
        return result;
    }

    public Transformer getAllNfcMemory(){
        GetAllNfcMemoryCommand command = new GetAllNfcMemoryCommand();
        Transformer result = messageBus.publishCommand(command);
        return result;
    }

    public Transformer getNfcMemoryLastReadTimestampByDeviceId(String deviceId){
        GetNfcMemoryLastReadTimeStampByDeviceIdCommand command = new GetNfcMemoryLastReadTimeStampByDeviceIdCommand(deviceId);
        Transformer result = messageBus.publishCommand(command);
        return result;
    }

}
