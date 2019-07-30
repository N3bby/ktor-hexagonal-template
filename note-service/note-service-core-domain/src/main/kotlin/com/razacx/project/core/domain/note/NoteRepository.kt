package com.razacx.project.core.domain.note

interface NoteRepository {

    fun save(note: Note)
    fun findAll(): List<Note>
    fun find(noteId: NoteId): Note

}
