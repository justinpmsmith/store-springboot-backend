package com.allianceseeds.api.services;

import com.allianceseeds.api.domain.commands.Command;

public interface MessageBusInt {
    Transformer publishCommand(Command command);

//    void subscribe(Class<? extends Command> commandType, CommandHandler<Command> handler);
}
