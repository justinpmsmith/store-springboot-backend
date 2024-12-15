package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.notifications.SendEmailCommand;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
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
