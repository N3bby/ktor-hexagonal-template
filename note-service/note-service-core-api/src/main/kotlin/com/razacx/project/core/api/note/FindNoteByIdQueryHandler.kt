package com.razacx.project.core.api.note

import com.razacx.project.core.api.QueryHandler
import com.razacx.project.core.domain.note.Note
import java.util.*

class FindNoteByIdQueryHandler: QueryHandler<FindNoteByIdQuery, Note> {

    override fun handle(query: FindNoteByIdQuery): Note {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQueryType(): Class<FindNoteByIdQuery> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
