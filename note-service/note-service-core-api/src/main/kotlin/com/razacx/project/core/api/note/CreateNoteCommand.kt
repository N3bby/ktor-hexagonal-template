package com.razacx.project.core.api.note

import com.razacx.project.core.api.Command
import com.razacx.project.core.domain.note.NoteId

data class CreateNoteCommand(val author: String, val message: String): Command<NoteId>
