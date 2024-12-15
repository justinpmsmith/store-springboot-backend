package com.accutrak.toolbox.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.presentation.resources.FirmwareResource;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.firmware.FirmwareTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class Firmware_Test {

    private FirmwareResource fwResource;

    private Firmware firmware = Firmware.builder().
            fileName("Fw_test.test").
            hardware("atfw_033_01").
            hash("TestHash").
            effectiveFrom(1716595200).
            effectiveTo(1843123423).
            site("*").
            blob("base64 blob").
            build();


    @Autowired
    public Firmware_Test(FirmwareResource fwResource) {
        this.fwResource = fwResource;
    }

//    @Test
//    void addDeleteFirmware_Test(){
//        FirmwareTransformer addTransformer = (FirmwareTransformer) fwResource.addFirmware(firmware);
//        Boolean addResult = (Boolean) addTransformer.getMeta().getSuccess();
//
//        FirmwareTransformer deleteTransformer = (FirmwareTransformer) fwResource.deleteFirmwareByFileName(firmware.getFileName());
//
//        Boolean deleteResult  = (Boolean) deleteTransformer.getMeta().getSuccess();
//
//        assertEquals(addResult, true);
//        assertEquals(deleteResult, true);
////
//    }
    @Test
    void getValidFirmware_Test(){
        fwResource.addFirmware(firmware);

        FirmwareTransformer transformer = (FirmwareTransformer) fwResource.getValidFirmwareInfo("*");

        List<Firmware> firmwareList = (List<Firmware>) transformer.getData();


        fwResource.deleteFirmwareByFileName(firmware.getFileName());

        assertNotNull(firmwareList);

    }

    @Test
    void getAllFirmware_Test(){
        fwResource.addFirmware(firmware);

        FirmwareTransformer transformer = (FirmwareTransformer) fwResource.getAllFirmware();
        List<Firmware> firmwareList = (List<Firmware>) transformer.getData();


        fwResource.deleteFirmwareByFileName(firmware.getFileName());

        assertEquals(firmwareList.size() > 0, true);

    }

//    @Test
//    void geFirmwareByFileName_Test(){
//        fwResource.addFirmware(firmware);
//
//        FirmwareTransformer transformer = (FirmwareTransformer) fwResource.getFirmwareByFileName(firmware.getFileName());
//        Firmware firmwareObj = (Firmware) transformer.getData();
//
//        fwResource.deleteFirmwareByFileName(firmware.getFileName());
//
//        assertNotNull(firmwareObj);
//
//    }

}
