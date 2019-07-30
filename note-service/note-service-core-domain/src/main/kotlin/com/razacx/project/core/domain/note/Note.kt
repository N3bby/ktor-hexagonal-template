package com.razacx.project.core.domain.note

import java.time.LocalDateTime
import java.util.*
import java.util.UUID.randomUUID

data class Note(val id: NoteId, val author: String, val message: String, val timestamp: LocalDateTime)
data class NoteId(val value: UUID)

// TODO This should be in a sharedTest sourceSet that can be added as a dependency.
fun defaultNote(
    id: NoteId = NoteId(randomUUID()),
    author: String = "John",
    message: String = "This is a message",
    timestamp: LocalDateTime = LocalDateTime.now()
): Note {
    return Note(id, author, message, timestamp)
}
