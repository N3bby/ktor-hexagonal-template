package com.razacx.project.adapter.rest.api

import com.razacx.project.core.api.CommandExecutor
import com.razacx.project.core.api.QueryExecutor
import com.razacx.project.core.api.note.CreateNoteCommand
import com.razacx.project.core.api.note.FindAllNotesQuery
import com.razacx.project.core.api.note.FindNoteByIdQuery
import com.razacx.project.core.domain.note.NoteId
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
import java.util.*

fun Routing.notesRoute() {
    val commandExecutor by inject<CommandExecutor>()
    val queryExecutor by inject<QueryExecutor>()

    route("note") {

        post("") {
            val createNoteCommand = call.receive<CreateNoteCommand>()
            val id = commandExecutor.handle(createNoteCommand)
            call.respond(Created, id)
        }

        get("") {
            val notes = queryExecutor.handle(FindAllNotesQuery())
            call.respond(OK, notes)
        }

        get("{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val note = queryExecutor.handle(FindNoteByIdQuery(NoteId(id)))
            call.respond(OK, note)
        }

    }
}
