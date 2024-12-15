package com.accutrak.toolbox.services.tagConfig;

import com.accutrak.toolbox.adapters.repositories.tagConfig.TagConfigRepoInt;
import com.accutrak.toolbox.adapters.repositories.user.UserRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.tagConfig.*;
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
public class TagConfigHandler {
    private final TagConfigRepoInt tagConfigRepo;

    UserRepo userRepo;

    private UnitOfWork<TagConfig> tagConfigUOW;

    @Autowired
    public TagConfigHandler(TagConfigRepoInt tagConfigRepo, UserRepo userRepo) {
        this.tagConfigRepo = tagConfigRepo;
        this.userRepo = userRepo;
        this.tagConfigUOW = new UnitOfWork<TagConfig>(this.tagConfigRepo);
    }

    public Transformer addTagConfig(Command command) {
        TagConfig tagConfig = ((AddTagConfigCommand) command).getTagConfig();
        tagConfigUOW.registerRepoOperation(tagConfig, UnitOfWorkInt.UnitActions.INSERT);
        tagConfigUOW.commit();


        return new TagConfigTransformer<>(true, tagConfig);
    }

    public Transformer deleteTagConfig(Command command){
        TagConfig tagConfig = ((DeleteTagConfigCommand) command).getTagConfig();
        tagConfigUOW.registerRepoOperation(tagConfig, UnitOfWorkInt.UnitActions.DELETE);
        tagConfigUOW.commit();

        return new TagConfigTransformer<>(true, tagConfig);
    }

    public Transformer bulkAddConfig(Command command){
        List<TagConfig> tagConfigList = ((BulkAddTagCommand) command).getTagConfigList();
        tagConfigUOW.registerBulkOperation(tagConfigList, UnitOfWorkInt.UnitActions.INSERT);
        tagConfigUOW.commit();

        return new TagConfigTransformer<>(true, null);
    }

    public Transformer getLastSetBySerial(Command command){
        String serial = ((GetLastSetBySerialCommand) command).getSerial();
        TagConfig lastSet = tagConfigRepo.getLastTagSetBySerial(serial);

        return new TagConfigTransformer<>(true, lastSet);
    }
    public Transformer getTagConfigsByUuId(Command command){
        String uuid = ((GetTagConfigsByUuidCommand) command).getUuid();
//        List<TagConfig> list = tagConfigRepo.getTagConfigsByUsuerId(uuid);
        List<TagConfig> list = tagConfigRepo.getTagConfigsByStringField("uuid", uuid);

        return new TagConfigTransformer<>(true, list);
    }

    public Transformer getTagConfigBysDeviceId(Command command){
        String deviceId = ((GetTagConfigByDeviceIdCommand) command).getDeviceId();
        List<TagConfig> list = tagConfigRepo.getTagConfigsByStringField("deviceId", deviceId);

        return new TagConfigTransformer<>(true, list);


    }

    public Transformer getTagConfigLastSetTimestampByUuid(Command command){
        String uuid = ((GetLatestTagConfigByUuidCommand) command).getUuid();
//        List<TagConfig> list = tagConfigRepo.getTagConfigsByUsuerId(uuid);
        List<TagConfig> tagConfigList = tagConfigRepo.getTagConfigsByStringField("uuid", uuid);
        TagConfigTransformer<Integer> result = new TagConfigTransformer<>(true, 0);
        result.setMostRecentLastChanged(tagConfigList);

        return result;

    }

    public Transformer getTagConfigLastSetTimestampByDeviceId(Command command){
        String deviceId = ((GetTagConfigLastSetTimestampByDeviceIdCommand) command).getDeviceId();

        List<TagConfig> tagConfigList = tagConfigRepo.getTagConfigsByStringField("deviceId", deviceId);
        TagConfigTransformer<Integer> result = new TagConfigTransformer<>(true, 0);
        result.setMostRecentLastChanged(tagConfigList);

        return result;


    }

    public Transformer getTagConfigsBySerial(Command command){
        String serial = ((GetTagConfigBySerialCommand) command).getSerial();

        List<TagConfig> tagConfigList = tagConfigRepo.getTagConfigsByStringField("serial", serial);
        return new TagConfigTransformer<>(true, tagConfigList);

    }

    public Transformer getTagConfigsByEmail(Command command){
        String email = ((GetTagConfigsByEmailCommand) command).getEmail();

        List<String> uuidList = userRepo.getUuidListByEmail(email);

        Map<String, List<TagConfig>> tagConfigMap = new HashMap<>();
        for (String uuid : uuidList) {
            List<TagConfig> tagConfigsForUuid = tagConfigRepo.getTagConfigsByStringField("uuid", uuid);
            tagConfigMap.put(uuid, tagConfigsForUuid);
        }

        return new TagConfigTransformer<>(true, tagConfigMap);

    }

    public Transformer getAllTagConfig(Command command){
        if(command == null){return null;}

        List<TagConfig> tagConfigList= tagConfigRepo.getAll();
        return new TagConfigTransformer<>(true, tagConfigList);


    }


}
