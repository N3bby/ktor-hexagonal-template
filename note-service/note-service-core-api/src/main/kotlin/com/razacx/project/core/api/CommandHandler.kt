package com.razacx.project.core.api

interface CommandHandler<INPUT: Command<OUTPUT>, OUTPUT> {

    fun handle(command: INPUT): OUTPUT
    fun getCommandType(): Class<INPUT>

}
