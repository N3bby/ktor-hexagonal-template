package com.razacx.project.core.api

import com.razacx.project.common.UnitTest
import com.razacx.project.core.domain.DomainRuntimeException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val DUMMY_MESSAGE = "Hello world!"

class CommandExecutorTest : UnitTest() {

    @Test
    internal fun `handle calls CommandHandler using given command if it exists and returns the result`() {
        val commandExecutor = CommandExecutor(CommandHandlerCollection(listOf(DummyCommandHandler())))
        assertThat(commandExecutor.handle(DummyCommand())).isEqualTo(DUMMY_MESSAGE)
    }

    @Test
    internal fun `handle throws exception when no CommandHandler is found for the given command`() {
        val commandExecutor = CommandExecutor(CommandHandlerCollection(emptyList()))
        val exception = assertThrows<DomainRuntimeException> { commandExecutor.handle(DummyCommand()) }
        assertThat(exception.message).isEqualTo("No handler found for command of type com.razacx.project.core.api.DummyCommand")
    }

}

private class DummyCommand : Command<String>

private class DummyCommandHandler : CommandHandler<DummyCommand, String> {
    override fun handle(command: DummyCommand): String {
        return DUMMY_MESSAGE
    }

    override fun getCommandType(): Class<DummyCommand> {
        return DummyCommand::class.java
    }
}
