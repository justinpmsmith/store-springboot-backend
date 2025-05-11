package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.domain.commands.pendingsale.CreatePendingSaleCommand;
import com.allianceseeds.api.domain.commands.pendingsale.GetAllPendingSalesCommand;
import com.allianceseeds.api.domain.commands.pendingsale.ProcessPaymentNotificationCommand;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class PendingSaleResource extends BaseResource {

    public PendingSaleResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer createPendingSale(CreatePendingSaleCommand command) {
        return messageBus.publishCommand(command);
    }

    public Transformer getAllPendingSales() {
        GetAllPendingSalesCommand command = new GetAllPendingSalesCommand();
        return messageBus.publishCommand(command);
    }

    public Transformer processPaymentNotification(ProcessPaymentNotificationCommand command) {
        return messageBus.publishCommand(command);
    }
}