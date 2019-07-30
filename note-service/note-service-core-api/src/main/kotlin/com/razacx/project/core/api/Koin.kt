package com.razacx.project.core.api

import com.razacx.project.core.api.note.*
import org.koin.dsl.module

fun createKoinModule() = module {

    single {
        CommandHandlerCollection(
            listOf(
                CreateNoteCommandHandler()
            )
        )
    }

    single {
        QueryHandlerCollection(
            listOf(
                FindAllNotesQueryHandler(),
                FindNoteByIdQueryHandler()
            )
        )
    }

    single { CommandExecutor(get()) }
    single { QueryExecutor(get()) }

}

data class CommandHandlerCollection(val commandHandlers: List<CommandHandler<*, *>>)
data class QueryHandlerCollection(val queryHandlers: List<QueryHandler<*, *>>)
