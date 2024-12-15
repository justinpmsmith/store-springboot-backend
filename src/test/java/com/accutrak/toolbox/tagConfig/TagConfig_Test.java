package com.accutrak.toolbox.tagConfig;

import com.accutrak.toolbox.domain.entities.TagConfig;
import com.accutrak.toolbox.presentation.resources.TagConfigResource;
import com.accutrak.toolbox.services.tagConfig.TagConfigTransformer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagConfig_Test {


    private TagConfigResource tagConfigResource;

    private String validSerial = "123456";
    private String validDeviceId = "someOtherDeviceId";
    private String validEmail = "jack@accutrak.co.za";

    private String validUuid = "8a8e1213-e3cf-4be9-8833-2faed8597889";

    TagConfig tagConfigObj = TagConfig.builder().
            deviceId(validDeviceId).
            uuid(validUuid).
            serial(validSerial).
            jsonData("{some: jasonData}").
            lastChanged(1715937199).build();;
    @Autowired
    public TagConfig_Test(TagConfigResource tagConfigResource) {
        this.tagConfigResource = tagConfigResource;
    }

    @Test
    @Order(1)
    void addTagConfigTest(){

        // arrange

       // act
        TagConfigTransformer<?> result = (TagConfigTransformer<?>) tagConfigResource.addTagConfig(tagConfigObj);

        assertEquals(true, result.getMeta().getSuccess());

    }

    @Test
    @Order(2)
    void getTagConfigBySerial_ValidSerial_Test(){
        // arrange act
        TagConfigTransformer<?> result = (TagConfigTransformer) tagConfigResource.getTagConfigsBySerial(validSerial);

        //assert
        assertNotNull(result);

    }

    @Test
    @Order(3)
    void getTagConfigByDeviceId_ValidDeviceId_Test(){
        TagConfigTransformer<?> result = (TagConfigTransformer<?>) tagConfigResource.getTagConfigsByDeviceId(validDeviceId);
        assertNotNull(result);


    }
    @Test
    @Order(4)
    void getTagConfigByEmail_ValidEmail_Test(){
        TagConfigTransformer<?> result= (TagConfigTransformer<?>) tagConfigResource.getTagConfigsByEmail(validEmail);
        assertNotNull(result);


    }

    @Test
    @Order(5)
    void deleteTagConfig_Test(){
        TagConfigTransformer<?> result = (TagConfigTransformer<?>) tagConfigResource.deleteTagConfig(tagConfigObj);
        assertNotNull(result);



    }


}
