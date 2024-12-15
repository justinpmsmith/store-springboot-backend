package com.accutrak.toolbox.domain.commands.device;

import com.accutrak.toolbox.domain.entities.Device;

import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

@Getter
public class AddDeviceCommand implements DeviceCommand {
    Device device;

    public AddDeviceCommand(Device device) {
        Validation.validateDevice(device);
        this.device = device;

    }



}
