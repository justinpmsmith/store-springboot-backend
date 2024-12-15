package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.services.MessageBus;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseResource {

    public final MessageBus messageBus;

    public BaseResource(MessageBus messageBus) {
        this.messageBus = messageBus;
    }
}
