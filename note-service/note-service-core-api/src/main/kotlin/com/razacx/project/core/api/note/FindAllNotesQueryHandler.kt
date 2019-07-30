package com.razacx.project.core.api.note

import com.razacx.project.core.api.QueryHandler
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteRepository

class FindAllNotesQueryHandler(private val noteRepository: NoteRepository): QueryHandler<FindAllNotesQuery, List<Note>> {

    override fun handle(query: FindAllNotesQuery): List<Note> {
        return noteRepository.findAll()
    }

    override fun getQueryType(): Class<FindAllNotesQuery> {
        return FindAllNotesQuery::class.java
    }

}


