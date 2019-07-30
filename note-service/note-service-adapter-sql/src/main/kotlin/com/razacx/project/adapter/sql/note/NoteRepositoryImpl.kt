package com.razacx.project.adapter.sql.note

import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.core.domain.note.NoteRepository

class NoteRepositoryImpl: NoteRepository {

    private val notes: MutableMap<NoteId, Note> = mutableMapOf()

    override fun save(note: Note) {
        notes[note.id] = note
    }

    override fun findAll(): List<Note> {
        return listOf(*notes.values.toTypedArray())
    }

    override fun find(noteId: NoteId): Note {
        return notes[noteId] ?: throw NoSuchElementException("No note found with id ${noteId.value}")
    }

}
