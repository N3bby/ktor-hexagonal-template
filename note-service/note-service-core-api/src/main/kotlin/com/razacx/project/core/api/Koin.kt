package com.razacx.project.core.api

import com.razacx.project.core.api.note.*
import org.koin.dsl.module

fun koinCoreApiModule() = module {

    single {
        CommandHandlerCollection(
            listOf(
                CreateNoteCommandHandler(get(), get(), get())
            )
        )
    }

    single {
        QueryHandlerCollection(
            listOf(
                FindAllNotesQueryHandler(get()),
                FindNoteByIdQueryHandler(get())
            )
        )
    }

    single { CommandExecutor(get()) }
    single { QueryExecutor(get()) }

}

data class CommandHandlerCollection(val commandHandlers: List<CommandHandler<*, *>>)
data class QueryHandlerCollection(val queryHandlers: List<QueryHandler<*, *>>)
