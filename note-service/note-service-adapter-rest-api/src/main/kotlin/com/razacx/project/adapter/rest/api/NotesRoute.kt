package com.razacx.project.adapter.rest.api

import com.razacx.project.core.api.note.CreateNoteCommand
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteRepository
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
import java.util.UUID.randomUUID

fun Routing.notesRoute() {
    val noteRepository by inject<NoteRepository>()
//    val dateProvider by inject<DateProvider>()

    route("note") {

        post("") {
            val createNoteJson = call.receive<CreateNoteCommand>()
            val id = randomUUID()
//            transaction {
                noteRepository.save(
                    Note(
                        id = id,
                        author = createNoteJson.author,
                        message = createNoteJson.message,
                        timestamp = LocalDateTime.now()
                    )
                )
//            }
            call.respond(Created, NoteIdJson(id))
        }

        get("") {
            val notes =
//                transaction {
                noteRepository.findAll()
//            }
            call.respond(OK, notes.map(::toJson))
        }

        get("{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val note =
//                transaction {
                    noteRepository.find(id)
//                }
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
