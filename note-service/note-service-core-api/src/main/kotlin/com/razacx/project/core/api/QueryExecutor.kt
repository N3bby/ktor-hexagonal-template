package com.razacx.project.core.api

import com.razacx.project.core.domain.DomainRuntimeException

class QueryExecutor(private val queryHandlerCollection: QueryHandlerCollection) {

    fun <INPUT : Query<OUTPUT>, OUTPUT> handle(query: INPUT): OUTPUT {
        val handler = queryHandlerCollection.queryHandlers.find { it.getQueryType() == query.javaClass }
        if (handler == null) {
            throw DomainRuntimeException("No handler found for query of type ${query.javaClass.name}")
        }
        return (handler as QueryHandler<INPUT, OUTPUT>).handle(query)
    }

}
