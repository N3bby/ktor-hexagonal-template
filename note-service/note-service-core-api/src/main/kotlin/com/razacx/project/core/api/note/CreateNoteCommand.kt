package com.razacx.project.core.api.note

import com.razacx.project.core.api.Command
import java.util.*

data class CreateNoteCommand(val author: String, val message: String): Command<UUID>
