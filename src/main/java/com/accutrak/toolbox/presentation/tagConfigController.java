package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.presentation.resources.TagConfigResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class tagConfigController {

    private TagConfigResource tagConfigResource;

    @Autowired
    public tagConfigController(TagConfigResource tagConfigResource) {
        this.tagConfigResource = tagConfigResource;
    }

    @PostMapping("/client/addTagConfig")
    public Transformer addTagConfig(@RequestBody TagConfig tagConfig){

        return tagConfigResource.addTagConfig(tagConfig);

    }

    @PostMapping("/client/bulkAddTagConfig")
    public Transformer bulkAddTagConfig(@RequestBody List<TagConfig> tagConfigList){

        return tagConfigResource.bulkAddTagConfig(tagConfigList);

    }

    @GetMapping("/admin/tagLastConfigSet") // lastTagConfigBySerial
    public Transformer tagLastConfigSet(@RequestParam String serial){
        return tagConfigResource.getLastSet(serial);
    }

    // allTagConfigBySerial
    @GetMapping("/admin/tagConfigsBySerial")
    public Transformer tagConfigBySerial(@RequestParam String serial){
        return tagConfigResource.getTagConfigsBySerial(serial);
    }

    // allTagConfig

    @GetMapping("/admin/tagConfigsByUuid")
    public Transformer tagConfigsByUserId(@RequestParam String uuid){
        return tagConfigResource.getTagConfigsByUuid(uuid);
    }
    @GetMapping("/admin/tagConfigsByDeviceId")
    public Transformer tagConfigsByDeviceId(@RequestParam String deviceId){
        return tagConfigResource.getTagConfigsByDeviceId(deviceId);
    }

    @GetMapping("/admin/tagConfigsByEmail")
    public Transformer tagConfigsByEmail(@RequestParam String email ){
        return tagConfigResource.getTagConfigsByEmail(email);

    }

    @GetMapping("/admin/allTagConfig")
    public Transformer allTagConfig(){
        return tagConfigResource.getAllTagConfig();

    }
    @GetMapping("/admin/tagConfigLastSetTimestampByUuid")
    public Transformer getTagConfigLastSetTimestampByUuid(@RequestParam String uuid ){
        return tagConfigResource.getTagConfigLastSetTimestampByUuid(uuid);

    }
    @GetMapping("/client/tagConfigLastSetTimestampByDeviceId")
    public Transformer getTagConfigLastSetTimestampByDeviceId(@RequestParam String deviceId ){
        return tagConfigResource.getTagConfigLastSetTimestampByDeviceId(deviceId);

    }

}
