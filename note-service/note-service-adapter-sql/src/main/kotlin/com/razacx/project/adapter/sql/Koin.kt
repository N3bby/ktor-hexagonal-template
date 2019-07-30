package com.razacx.project.adapter.sql

import com.razacx.project.adapter.sql.note.NoteRepositoryImpl
import com.razacx.project.core.domain.note.NoteRepository
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

fun koinAdapterSqlModule() = module {

    singleBy<NoteRepository, NoteRepositoryImpl>()

}
