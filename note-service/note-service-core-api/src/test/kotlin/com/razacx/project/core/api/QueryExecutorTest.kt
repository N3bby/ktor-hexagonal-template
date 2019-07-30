package com.razacx.project.core.api

import com.razacx.project.common.UnitTest
import com.razacx.project.core.domain.DomainRuntimeException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

private const val DUMMY_MESSAGE = "Hello world!"

class QueryExecutorTest: UnitTest() {

    @Test
    internal fun `handle calls QueryHandler using given query if it exists and returns the result`() {
        val queryExecutor = QueryExecutor(QueryHandlerCollection(listOf(DummyQueryHandler())))
        Assertions.assertThat(queryExecutor.handle(DummyQuery())).isEqualTo(DUMMY_MESSAGE)
    }

    @Test
    internal fun `handle throws exception when no QueryHandler is found for the given query`() {
        val queryExecutor = QueryExecutor(QueryHandlerCollection(emptyList()))
        org.junit.jupiter.api.assertThrows<DomainRuntimeException> { queryExecutor.handle(DummyQuery()) }
    }

}

private class DummyQuery : Query<String>

private class DummyQueryHandler : QueryHandler<DummyQuery, String> {
    override fun handle(query: DummyQuery): String {
        return DUMMY_MESSAGE
    }

    override fun getQueryType(): Class<DummyQuery> {
        return DummyQuery::class.java
    }
}

