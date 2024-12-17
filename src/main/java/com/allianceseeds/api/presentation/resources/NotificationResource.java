package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.domain.commands.notifications.SendEmailCommand;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class NotificationResource extends BaseResource{
    public NotificationResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Boolean sendDummyEmail(){
        SendEmailCommand command = new SendEmailCommand("justinpmsmith@outlook.com", "hello world", "hello world");
        Transformer result = messageBus.publishCommand(command);
        return true;
    }
}
