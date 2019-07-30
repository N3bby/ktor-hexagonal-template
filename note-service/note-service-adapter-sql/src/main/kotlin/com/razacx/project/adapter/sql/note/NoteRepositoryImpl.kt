package com.razacx.project.adapter.sql.note

import com.razacx.project.adapter.sql.extension.toSystemLocalDateTime
import com.razacx.project.adapter.sql.extension.toUtcJodaTime
import com.razacx.project.adapter.sql.note.NoteTable.id
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.core.domain.note.NoteRepository
import org.jetbrains.exposed.sql.*

object NoteTable: Table("note") {
    val id = uuid("id").primaryKey()
    val author = varchar("author", 50)
    val message = text("message")
    val timestamp = datetime("timestamp")
}

fun toDomain(resultRow: ResultRow): Note {
    return Note(
        id = NoteId(resultRow[NoteTable.id]),
        author = resultRow[NoteTable.author],
        message = resultRow[NoteTable.message],
        timestamp = resultRow[NoteTable.timestamp].toSystemLocalDateTime()
    )
}

class NoteRepositoryImpl: NoteRepository {

    override fun save(note: Note) {
        NoteTable.insert {
            it[id] = note.id.value
            it[author] = note.author
            it[message] = note.message
            it[timestamp] = note.timestamp.toUtcJodaTime()
        }
    }

    override fun findAll(): List<Note> {
        return NoteTable.selectAll().map(::toDomain)
    }

    override fun find(noteId: NoteId): Note {
        return NoteTable.select { id eq noteId.value }
            .map(::toDomain)
            .first()
    }

}
