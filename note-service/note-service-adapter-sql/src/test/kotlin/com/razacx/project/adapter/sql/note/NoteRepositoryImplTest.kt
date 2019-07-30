package com.razacx.project.adapter.sql.note

import com.razacx.project.core.domain.note.NoteId
import com.razacx.project.core.domain.note.defaultNote
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID.randomUUID

internal class NoteRepositoryImplTest {

    private val repository = NoteRepositoryImpl()

    @Test
    fun `save saves note`() {
        val note = defaultNote()
        repository.save(note)

        assertThat(repository.findAll()).contains(note)
    }

    @Test
    fun `findAll returns all saved notes`() {
        val expectedNotes = listOf(defaultNote(), defaultNote())
        expectedNotes.forEach { repository.save(it) }

        val notes = repository.findAll()

        assertThat(notes).isEqualTo(expectedNotes)
    }

    @Test
    fun `find returns note for given id if it exists`() {
        val id = NoteId(randomUUID())
        val expectedNote = defaultNote(id)
        repository.save(expectedNote)

        val note = repository.find(id)

        assertThat(note).isEqualTo(expectedNote)
    }

    @Test
    fun `find throws NoSuchElementException if note for given id does not exist`() {
        val id = NoteId(randomUUID())
        val exception = assertThrows<NoSuchElementException> { repository.find(id) }
        assertThat(exception.message).isEqualTo("No note found with id ${id.value}")
    }

}
