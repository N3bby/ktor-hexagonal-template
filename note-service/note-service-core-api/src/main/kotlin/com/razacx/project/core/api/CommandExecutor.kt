package com.razacx.project.core.api

import com.razacx.project.core.domain.DomainRuntimeException

class CommandExecutor(private val commandHandlerCollection: CommandHandlerCollection) {

    fun <INPUT : Command<OUTPUT>, OUTPUT> handle(command: INPUT): OUTPUT {
        val handler = commandHandlerCollection.commandHandlers.find { it.getCommandType() == command.javaClass }
        if (handler == null) {
            throw DomainRuntimeException("No handler found for command of type ${command.javaClass.name}")
        }
        return (handler as CommandHandler<INPUT, OUTPUT>).handle(command)
    }

}
