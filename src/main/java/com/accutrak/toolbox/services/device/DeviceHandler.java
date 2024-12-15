package com.accutrak.toolbox.services.device;

import com.accutrak.toolbox.adapters.repositories.device.DeviceRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.device.AddDeviceCommand;
import com.accutrak.toolbox.domain.entities.Device;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceHandler {

    DeviceRepo repo;

    UnitOfWork<Device> uow;

    @Autowired
    public DeviceHandler(DeviceRepo repo) {
        this.repo = repo;
        this.uow = new UnitOfWork<Device>(this.repo);
    }

    public Transformer addDeviceInfo(Command command){
        Device device = ((AddDeviceCommand) command).getDevice();

        uow.registerRepoOperation(device, UnitOfWorkInt.UnitActions.INSERT);
        uow.commit();
        DeviceTransformer transformer = new DeviceTransformer<>(true, null);
        return transformer;

    }
}
