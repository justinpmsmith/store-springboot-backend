package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.presentation.resources.FirmwareResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FirmwareController {

    private FirmwareResource fwResource;

    @Autowired
    public FirmwareController(FirmwareResource fwResource) {
        this.fwResource = fwResource;
    }

    @PostMapping("/admin/addFirmware")
    public Transformer addFirmware(@RequestBody Firmware firmware){
        return fwResource.addFirmware(firmware);
    }

    @DeleteMapping("/admin/deleteFirmwareByFileName")
    public Transformer deleteFirmwareByFileName(@RequestParam String fileName){
        return fwResource.deleteFirmwareByFileName(fileName);
    }

    @DeleteMapping("/admin/deleteFirmwareByFileNameAndSite")
    public Transformer deleteFirmwareByFileNameAndSite(@RequestParam String fileName, String site){
        return fwResource.deleteFirmwareByFileNameAndSite(fileName, site);
    }

    @GetMapping("/client/getValidFirmware")
    public Transformer getValidFirmware(@RequestParam String site){
        return fwResource.getValidFirmware(site);
    }

    @GetMapping("/client/getValidFirmwareInfo")
    public Transformer getValidFirmwareInfo(@RequestParam String site){
        return fwResource.getValidFirmwareInfo(site);
    }

    @GetMapping("/client/getAllFirmware")
    public Transformer getAllFirmware(){
        return fwResource.getAllFirmware();
    }

    @GetMapping("/client/getAllFirmwareInfo")
    public Transformer getAllFirmwareInfo(){
        return fwResource.getAllFirmwareInfo();
    }

    @GetMapping("/client/getFirmwareByFileName")
    public Transformer getFirmwareByFileName(@RequestParam String fileName){
        return fwResource.getFirmwareByFileName(fileName);
    }


}
