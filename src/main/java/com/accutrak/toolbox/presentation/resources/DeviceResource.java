package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.device.AddDeviceCommand;
import com.accutrak.toolbox.domain.entities.Device;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.device.DeviceTransformer;
import org.springframework.stereotype.Component;

@Component
public class DeviceResource extends BaseResource{
    public DeviceResource(MessageBus messageBus) {
        super(messageBus);
    }

    public DeviceTransformer addDeviceInfo(Device device){
        AddDeviceCommand command = new AddDeviceCommand(device);
        Transformer result = messageBus.publishCommand(command);

        return  ((DeviceTransformer) result);

    }
}
