package com.razacx.project.adapter.rest.api

import com.razacx.project.core.api.CommandExecutor
import com.razacx.project.core.api.QueryExecutor
import com.razacx.project.core.api.note.CreateNoteCommand
import com.razacx.project.core.api.note.FindAllNotesQuery
import com.razacx.project.core.api.note.FindNoteByIdQuery
import com.razacx.project.core.domain.note.Note
import io.ktor.application.call
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import java.time.LocalDateTime
import java.util.*

fun Routing.notesRoute() {
    val commandExecutor by inject<CommandExecutor>()
    val queryExecutor by inject<QueryExecutor>()

    route("note") {

        post("") {
            val createNoteCommand = call.receive<CreateNoteCommand>()
            val id = commandExecutor.handle(createNoteCommand)
            call.respond(Created, NoteIdJson(id))
        }

        get("") {
            val notes = queryExecutor.handle(FindAllNotesQuery())
            call.respond(OK, notes.map(::toJson))
        }

        get("{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val note = queryExecutor.handle(FindNoteByIdQuery(id))
            call.respond(OK, toJson(note))
        }

    }
}

data class NoteIdJson(val id: UUID)
data class NoteJson(val id: UUID, val author: String, val message: String, val timestamp: LocalDateTime)

fun toJson(note: Note): NoteJson {
    return NoteJson(
        id = note.id,
        author = note.author,
        message = note.message,
        timestamp = note.timestamp
    )
}
