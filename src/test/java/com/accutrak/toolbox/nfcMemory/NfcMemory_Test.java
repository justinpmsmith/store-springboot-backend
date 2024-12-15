package com.accutrak.toolbox.nfcMemory;

import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.presentation.resources.NfcMemoryResource;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.firmware.FirmwareTransformer;
import com.accutrak.toolbox.services.nfcMemory.NfcMemoryTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NfcMemory_Test {

    private final String validSerial = "123456";
    private final String validDeviceId = "someOtherDeviceId";
    private final String validEmail = "jack@gmail.com";

    private final String validUuid = "8a8e1213-e3cf-4be9-8833-2faed8597889";

    private final int validTimestamp = 1704067200; // 01/01/2024

    private final String validFileName = "Test_01_01_2024";

    private final String data = "Test some data";

    NfcMemory nfcMemory = NfcMemory.builder().
            uuid(validUuid).
            deviceId(validDeviceId).
            serial(validSerial).
            scanTimestamp(validTimestamp).
            jsonData(data).build();

    private NfcMemoryResource nfcMemoryResource;

    @Autowired
    public NfcMemory_Test(NfcMemoryResource nfcMemoryResource) {
        this.nfcMemoryResource = nfcMemoryResource;
    }

    @Test
    void addNfcMemory_Test(){
        Transformer result =  nfcMemoryResource.addNfcMemory(nfcMemory);

        assertEquals(((NfcMemoryTransformer<?>) result).getMeta().getSuccess(), true);

    }

    @Test
    void tagLastNfcReadBySerial_Test(){
        Transformer result =  nfcMemoryResource.getLastReadBySerial(validSerial);
        assertNotNull(((NfcMemoryTransformer<?>) result).getData());

    }

    @Test
    void tagLastNfcReadByDeviceId_Test(){
        Transformer result =  nfcMemoryResource.getLastReadByDeviceId(validDeviceId);
        assertNotNull(((NfcMemoryTransformer<?>) result).getData());
    }

    @Test
    void nfcMemoryBySerial_Test(){
        Transformer result =  nfcMemoryResource.getNfcMemoryBySerial(validSerial);
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));
    }

    @Test
    void nfcMemoryByUuid_Test(){
        Transformer result =  nfcMemoryResource.getNfcMemoryByUuid(validUuid);
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));
    }

    @Test
    void nfcMemoryByEmail_Test(){
        Transformer result =  nfcMemoryResource.getNfcMemoryByEmail(validEmail);
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));
    }

    @Test
    void nfcMemoryByDeviceId_Test(){
        Transformer result =  nfcMemoryResource.getNfcMemoryByDeviceId(validDeviceId);
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));
    }

    @Test
    void nfcMemoryLastReadTimeStampByDeviceId_Test(){
        Transformer result =  nfcMemoryResource.getNfcMemoryLastReadTimestampByDeviceId(validDeviceId);
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));

    }

    @Test
    void allNfcMemory_Test(){
        Transformer result =  nfcMemoryResource.getAllNfcMemory();
        assertNotNull(((((NfcMemoryTransformer<?>) result).getData())));


    }

}
