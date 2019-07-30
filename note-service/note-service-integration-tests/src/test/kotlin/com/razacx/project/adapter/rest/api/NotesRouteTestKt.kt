package com.razacx.project.adapter.rest.api

import com.razacx.project.IntegrationTest
import com.razacx.project.common.util.date.DateProvider
import com.razacx.project.core.api.note.CreateNoteCommand
import com.razacx.project.core.domain.note.Note
import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.launcher.koinRootModule
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.ktor.ext.inject
import java.time.LocalDateTime

class NotesRouteTestKt: IntegrationTest() {

    private val dateProvider = mockk<DateProvider>()

    override fun overrideKoinModules() {
        super.overrideKoinModules()
        koinRootModule.single(override = true) { dateProvider }
        every { dateProvider.now() }.returns(LocalDateTime.now())
    }

    @Test
    fun `post creates note with random id and current timestamp`(): Unit = integrationTest {
        val dateProvider by application.inject<DateProvider>()
        val author = "some author"
        val message = "some message"

        val createNoteResponse = post(this, "/note", toJson(CreateNoteCommand(author, message)))
        assertThat(createNoteResponse.status()).isEqualTo(Created)
        val noteId = fromJson<NoteId>(createNoteResponse.content!!)

        val getNoteResponse = get(this, "/note/${noteId.value}")
        assertThat(getNoteResponse.status()).isEqualTo(OK)
        val note = fromJson<Note>(getNoteResponse.content!!)

        assertThat(note)
            .isEqualTo(
                Note(
                    id = noteId,
                    author = author,
                    message = message,
                    timestamp = dateProvider.now()
                )
            )
    }

    @Test
    fun `get gives list of all notes`(): Unit = integrationTest {
        val dateProvider by application.inject<DateProvider>()
        val author = "some author"
        val message = "some message"
        val noteId1 = fromJson<NoteId>(post(this, "/note", toJson(CreateNoteCommand(author, message))).content!!)
        val noteId2 = fromJson<NoteId>(post(this, "/note", toJson(CreateNoteCommand(author, message))).content!!)

        val notes = fromJson<List<Note>>(get(this, "/note").content!!)
        assertThat(notes).containsExactlyInAnyOrder(
            Note(
                id = noteId1,
                author = author,
                message = message,
                timestamp = dateProvider.now()
            ),
            Note(
                id = noteId2,
                author = author,
                message = message,
                timestamp = dateProvider.now()
            )
        )
    }

}
