package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.services.MessageBus;

public class BaseResource {

    public final MessageBus messageBus;

    public BaseResource(MessageBus messageBus) {
        this.messageBus = messageBus;
    }
}
