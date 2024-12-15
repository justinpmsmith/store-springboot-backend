package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Device;
import com.accutrak.toolbox.presentation.resources.DeviceResource;
import com.accutrak.toolbox.services.device.DeviceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DeviceController {

    private DeviceResource deviceResource;

    @Autowired
    public DeviceController(DeviceResource deviceResource) {
        this.deviceResource = deviceResource;
    }

    @PostMapping("/client/addDeviceInfo")
    public DeviceTransformer addNDevice(@RequestBody Device device){
        return deviceResource.addDeviceInfo(device);
    }
}
