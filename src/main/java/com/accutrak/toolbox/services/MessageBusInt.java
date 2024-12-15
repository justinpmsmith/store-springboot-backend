package com.accutrak.toolbox.services;

import com.accutrak.toolbox.domain.commands.Command;

public interface MessageBusInt {
    Transformer publishCommand(Command command);

//    void subscribe(Class<? extends Command> commandType, CommandHandler<Command> handler);
}
