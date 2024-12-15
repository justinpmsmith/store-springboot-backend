package com.accutrak.toolbox.services.nfcMemory;

import com.accutrak.toolbox.adapters.repositories.nfcMemory.NfcMemoryRepoInt;
import com.accutrak.toolbox.adapters.repositories.user.UserRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.nfcMemory.*;
import com.accutrak.toolbox.domain.commands.tagConfig.GetTagConfigByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.user.GetUuidByEmailCommand;
import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NfcMemoryHandler {

    private final NfcMemoryRepoInt nfcMemoryRepo;

    private final UserRepo userRepo;

    private UnitOfWork<NfcMemory> nfcMemoryUOW;

    @Autowired
    public NfcMemoryHandler(NfcMemoryRepoInt nfcMemoryRepo, UserRepo userRepo) {
        this.nfcMemoryRepo = nfcMemoryRepo;
        this.userRepo = userRepo;
        this.nfcMemoryUOW = new UnitOfWork<NfcMemory>(this.nfcMemoryRepo);
    }

    public Transformer addNfcMemory(Command command){
        NfcMemory nfcMemory = ((AddNfcMemoryCommand) command).getNfcMemory();
        nfcMemoryUOW.registerRepoOperation(nfcMemory, UnitOfWorkInt.UnitActions.INSERT);
        nfcMemoryUOW.commit();

        return new NfcMemoryTransformer<>(true, nfcMemory);
    }

    public Transformer bulkAddNfcMemory(Command command){
        List<NfcMemory> nfcMemoryList = ((BulkAddNfcMemoryCommand) command).getNfcMemoryList();

        nfcMemoryUOW.registerBulkOperation(nfcMemoryList, UnitOfWorkInt.UnitActions.INSERT );
        nfcMemoryUOW.commit();

        return new NfcMemoryTransformer<>(true, null);

    }

    public Transformer getLastReadBySerial(Command command){
        String serial = ((GetLastReadBySerialCommand) command).getSerial();
        NfcMemory lastRead = nfcMemoryRepo.getLastReadBySerial(serial);

        return new NfcMemoryTransformer<>(true, lastRead);
    }

    public Transformer getNfcMemoryLastReadByDeviceId(Command command){
        String deviceId = ((GetNfcMemoryLastReadByDeviceIdCommand) command).getDeviceId();
        NfcMemory lastRead = nfcMemoryRepo.getLastReadByStringField("deviceId", deviceId);

        return new NfcMemoryTransformer<>(true, lastRead);

    }

    public Transformer getNfcMemoryByUuid(Command command){
        String uuid = ((GetNfcMemoryByUuidCommand) command).getUuid();

        List<NfcMemory> nfcMemoryList =nfcMemoryRepo.getNfcMemorysByUsuerId(uuid);
        return new NfcMemoryTransformer<>(true, nfcMemoryList);
    }

    public Transformer getNfcMemoryBySerial(Command command){
        String serial = ((GetNfcMemoryBySerialCommand) command).getSerial();

        List<NfcMemory> nfcMemoryList = nfcMemoryRepo.getNfcMemorysByStringField("serial", serial);
        return new NfcMemoryTransformer<>(true, nfcMemoryList);
    }

    public Transformer getNfcMemoryByDeviceId(Command command){
        String deviceId = ((GetNfcMemoryByDeviceIdCommand) command).getDeviceId();
        List<NfcMemory> nfcMemoryList = nfcMemoryRepo.getNfcMemorysByStringField("deviceId", deviceId);
        NfcMemoryTransformer transformer = new NfcMemoryTransformer(true, nfcMemoryList);
        return transformer;
    }

    public Transformer getNfcMemoryByEmail(Command command){
        String email = ((GetNfcMemoryByEmailCommand) command).getEmail();
        List<String> uuidList = userRepo.getUuidListByEmail(email);

        Map<String, List<NfcMemory>> nfcMemoryMap = new HashMap<>();

        for (String uuid : uuidList){
            List<NfcMemory> nfcMemoryList = nfcMemoryRepo.getNfcMemorysByStringField("uuid", uuid);
            nfcMemoryMap.put(uuid, nfcMemoryList);
        }

        return new NfcMemoryTransformer<>(true, nfcMemoryMap);

    }

    public Transformer getNfcMemoryLastReadTimestampByDeviceId(Command command){
        String deviceId = ((GetNfcMemoryLastReadTimeStampByDeviceIdCommand) command).getDeviceId();
        NfcMemory lastRead = nfcMemoryRepo.getLastReadByStringField("deviceId", deviceId);
        NfcMemoryTransformer<?> transformer;

        if(lastRead == null){
            return new NfcMemoryTransformer<>(true, 0);
        }
        int timestamp = lastRead.getScanTimestamp();

        return new NfcMemoryTransformer<>(true, timestamp);


    }

    public Transformer getAllNfcMemory(Command command){
        if(command == null){return null;}

        List<NfcMemory> nfcMemoryList = nfcMemoryRepo.getAll();

        return new NfcMemoryTransformer<>(true, nfcMemoryList);
    }




}
