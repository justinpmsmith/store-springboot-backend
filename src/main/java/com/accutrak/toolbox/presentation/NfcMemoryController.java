package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.presentation.resources.NfcMemoryResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class NfcMemoryController {

    private NfcMemoryResource nfcMemoryResource;

    @Autowired
    public NfcMemoryController(NfcMemoryResource nfcMemoryResource) {
        this.nfcMemoryResource = nfcMemoryResource;
    }

    @PostMapping("/client/addNfcMemory")
    public Transformer addNfcMemory(@RequestBody NfcMemory nfcMemory){
        return nfcMemoryResource.addNfcMemory(nfcMemory);
    }

    @PostMapping("/client/bulkAddNfcMemory")
    public Transformer addNfcMemory(@RequestBody List<NfcMemory> nfcMemoryList){

        return nfcMemoryResource.bulkAddNfcMemory(nfcMemoryList);
    }

    @GetMapping("/admin/tagLastNfcReadBySerial") // lastTagConfigBySerial
    public  Transformer tagLastNfcReadBySerial(@RequestParam String serial){
        return nfcMemoryResource.getLastReadBySerial(serial);
    }

    @GetMapping("/admin/tagLastNfcReadByDeviceId") // lastTagConfigBySerial
    public  Transformer tagLastNfcReadByDeviceId(@RequestParam String deviceId){
        return nfcMemoryResource.getLastReadByDeviceId(deviceId);
    }

    @GetMapping("/admin/nfcMemoryBySerial")
    public Transformer nfcMemoryBySerial(@RequestParam String serial){
        return nfcMemoryResource.getNfcMemoryBySerial(serial);
    }

    @GetMapping("/admin/getNfcMemoryByUuid")
    public Transformer nfcMemoryByUuid(@RequestParam String uuid){
        return nfcMemoryResource.getNfcMemoryByUuid(uuid);
    }

    @GetMapping("/admin/getNfcMemoryByEmail")
    public Transformer nfcMemoryByEmail(@RequestParam String email){
        return nfcMemoryResource.getNfcMemoryByEmail(email);
    }

    @GetMapping("/admin/getNfcMemoryByDeviceId")
    public Transformer nfcMemoryByDeviceId(@RequestParam String deviceId){
        return nfcMemoryResource.getNfcMemoryByDeviceId(deviceId);
    }
    @GetMapping("/admin/allNfcMemory")
    public Transformer allNfcMemory(){
        return nfcMemoryResource.getAllNfcMemory();

    }

    @GetMapping("/client/getNfcMemoryLastReadTimeStampByDeviceId")
    public Transformer nfcMemoryLastReadTimeStampByDeviceId(@RequestParam String deviceId){
        return nfcMemoryResource.getNfcMemoryLastReadTimestampByDeviceId(deviceId);
    }



}
