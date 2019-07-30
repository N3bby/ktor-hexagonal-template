package com.razacx.project.core.api.note

import com.razacx.project.core.api.QueryHandler
import com.razacx.project.core.domain.note.Note

class FindAllNotesQueryHandler: QueryHandler<FindAllNotesQuery, List<Note>> {

    override fun handle(query: FindAllNotesQuery): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQueryType(): Class<FindAllNotesQuery> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


