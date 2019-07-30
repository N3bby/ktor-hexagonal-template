package com.razacx.project.core.api.note

import com.razacx.project.common.UnitTest
import com.razacx.project.common.util.date.DateProvider
import com.razacx.project.common.util.uuid.UUIDProvider
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.core.domain.note.NoteRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class CreateNoteCommandHandlerTest : UnitTest() {

    private val id = UUID.randomUUID()
    private val now = LocalDateTime.now()

    private val noteRepository = mockk<NoteRepository>(relaxed = true)
    private val uuidProvider = mockk<UUIDProvider>()
    private val dateProvider = mockk<DateProvider>()

    private val handler = CreateNoteCommandHandler(noteRepository, uuidProvider, dateProvider)

    @Test
    fun `handle creates and persists a note created from the given command with a random id`() {
        every { uuidProvider.generateUUID() }.returns(id)
        every { dateProvider.now() }.returns(now)

        val command = CreateNoteCommand("John", "This is a note")
        handler.handle(command)

        verify(exactly = 1) { noteRepository.save(Note(NoteId(id), command.author, command.message, now)) }
    }

    @Test
    fun `getCommandType returns CreateNoteCommand java type`() {
        assertThat(handler.getCommandType()).isEqualTo(CreateNoteCommand::class.java)
    }

}
