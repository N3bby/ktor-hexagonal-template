package com.razacx.project.core.api.note

import com.razacx.project.common.util.date.DateProvider
import com.razacx.project.common.util.uuid.UUIDProvider
import com.razacx.project.core.api.CommandHandler
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteRepository
import java.util.*

class CreateNoteCommandHandler(
    private val noteRepository: NoteRepository,
    private val uuidProvider: UUIDProvider,
    private val dateProvider: DateProvider
) : CommandHandler<CreateNoteCommand, UUID> {

    override fun handle(command: CreateNoteCommand): UUID {
        val id = uuidProvider.generateUUID()
        noteRepository.save(
            Note(
                id,
                command.author,
                command.message,
                dateProvider.now()
            )
        )
        return id
    }

    override fun getCommandType(): Class<CreateNoteCommand> {
        return CreateNoteCommand::class.java
    }

}
