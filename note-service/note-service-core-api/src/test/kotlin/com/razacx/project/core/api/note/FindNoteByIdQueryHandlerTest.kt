package com.razacx.project.core.api.note

import com.razacx.project.common.UnitTest
import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.core.domain.note.NoteRepository
import com.razacx.project.core.domain.note.defaultNote
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class FindNoteByIdQueryHandlerTest: UnitTest() {

    private val noteRepository = mockk<NoteRepository>()

    private val handler = FindNoteByIdQueryHandler(noteRepository)

    @Test
    fun `handle returns Note from repository`() {
        val id = NoteId(randomUUID())
        val expectedNote = defaultNote(id)
        every { noteRepository.find(id) }.returns(expectedNote)

        assertThat(handler.handle(FindNoteByIdQuery(id))).isEqualTo(expectedNote)
    }

    @Test
    fun `getQueryType returns FindNoteByIdQuery java type`() {
        assertThat(handler.getQueryType()).isEqualTo(FindNoteByIdQuery::class.java)
    }

}
