package com.razacx.project.core.api.note

import com.razacx.project.core.api.Query
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteId
import java.util.*

class FindNoteByIdQuery(val id: NoteId): Query<Note>
