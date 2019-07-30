package com.razacx.project.core.domain.note

import java.util.*

interface NoteRepository {

    fun save(note: Note)
    fun findAll(): List<Note>
    fun find(id: UUID): Note

}
