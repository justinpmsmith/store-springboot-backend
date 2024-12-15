package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.user.*;
import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.user.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResource extends BaseResource{

    @Autowired
    public UserResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer requestOtp(Otp otp){
        String email = otp.getEmail();
        String deviceId = otp.getDeviceId();
        RequestOtpCommand command = new RequestOtpCommand(email, deviceId);

        return messageBus.publishCommand(command);
    }

    public Transformer addUser(User user){

        AddUserCommand command = new AddUserCommand(user);


        return messageBus.publishCommand(command);
    }

    public Transformer updateUser(User user){
        UpdateUserCommand command = new UpdateUserCommand(user);

        return messageBus.publishCommand(command);
    }

    public Transformer migrateUser(User user){
        MigrateUserCommand command = new MigrateUserCommand(user);

        return messageBus.publishCommand(command);
    }

    public Transformer getUuidByEmail(String email){
        GetUuidByEmailCommand command = new GetUuidByEmailCommand(email);
        return messageBus.publishCommand(command);
    }

    public Transformer getTagConfigLevelByUuid(String uuid){
        GetTagConfigLevelByUuidCommand command = new GetTagConfigLevelByUuidCommand(uuid);
        return messageBus.publishCommand(command);

    }
}
