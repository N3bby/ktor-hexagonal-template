package com.razacx.project.core.domain.note

import java.time.LocalDateTime
import java.util.*
import java.util.UUID.randomUUID

data class Note(val id: UUID, val author: String, val message: String, val timestamp: LocalDateTime)

// TODO This should be in a sharedTest sourceSet that can be shared with other dependencies.
fun defaultNote(
    id: UUID = randomUUID(),
    author: String = "John",
    message: String = "This is a message",
    timestamp: LocalDateTime = LocalDateTime.now()
): Note {
    return Note(id, author, message, timestamp)
}
