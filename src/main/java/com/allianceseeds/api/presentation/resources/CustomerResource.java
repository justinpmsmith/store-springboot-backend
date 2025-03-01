package com.allianceseeds.api.presentation.resources;

import com.allianceseeds.api.domain.commands.customer.ContactUsCommand;
import com.allianceseeds.api.domain.commands.customer.SellSomethingCommand;
import com.allianceseeds.api.services.MessageBus;
import com.allianceseeds.api.services.Transformer;
import org.springframework.stereotype.Component;

@Component
public class CustomerResource extends BaseResource{
    public CustomerResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer contactUS(ContactUsCommand contactUsCommand) {
        ContactUsCommand command = contactUsCommand;
        return messageBus.publishCommand(command);

    }

    public Transformer sellSomething(SellSomethingCommand sellSomethingCommand) {
        SellSomethingCommand command = sellSomethingCommand;
        return messageBus.publishCommand(command);

    }
}
