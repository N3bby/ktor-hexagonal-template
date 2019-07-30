package com.razacx.project.core.api.note

import com.razacx.project.core.api.QueryHandler
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteRepository
import java.util.*

class FindNoteByIdQueryHandler(private val noteRepository: NoteRepository): QueryHandler<FindNoteByIdQuery, Note> {

    override fun handle(query: FindNoteByIdQuery): Note {
        return noteRepository.find(query.id)
    }

    override fun getQueryType(): Class<FindNoteByIdQuery> {
        return FindNoteByIdQuery::class.java
    }

}
