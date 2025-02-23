package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.services.MessageBus;
import org.springframework.stereotype.Component;

@Component
public class AuthResource extends BaseResource{
    public AuthResource(MessageBus messageBus) {
        super(messageBus);
    }
}
