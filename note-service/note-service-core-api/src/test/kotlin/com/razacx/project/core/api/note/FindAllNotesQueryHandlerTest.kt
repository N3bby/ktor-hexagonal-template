package com.razacx.project.core.api.note

import com.razacx.project.common.UnitTest
import com.razacx.project.core.domain.note.NoteRepository
import com.razacx.project.core.domain.note.defaultNote
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FindAllNotesQueryHandlerTest: UnitTest() {

    private val noteRepository = mockk<NoteRepository>()

    private val handler = FindAllNotesQueryHandler(noteRepository)

    @Test
    fun `handle calls NoteRepository and returns all found notes`() {
        val expectedNotes = listOf(defaultNote(), defaultNote())
        every { noteRepository.findAll() }.returns(expectedNotes)

        assertThat(handler.handle(FindAllNotesQuery())).isEqualTo(expectedNotes)
    }

    @Test
    fun `getQueryType returns FindAllNotesQuery java type`() {
        assertThat(handler.getQueryType()).isEqualTo(FindAllNotesQuery::class.java)
    }

}
