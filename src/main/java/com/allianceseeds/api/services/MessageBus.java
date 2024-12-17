package com.allianceseeds.api.services;

import com.allianceseeds.api.domain.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class MessageBus implements MessageBusInt{

    private final Map<Class<? extends Command>, Function<Command, Transformer>> commandHandlers;

    @Autowired
    public MessageBus(Handlers handlers) {
        commandHandlers = handlers.getCmdHandlers();
    }

    @Override
    public Transformer publishCommand(Command command) {
        Function<Command, Transformer> handler = commandHandlers.get(command.getClass());
        Transformer result = handler.apply(command);
        return result;
    }
}
