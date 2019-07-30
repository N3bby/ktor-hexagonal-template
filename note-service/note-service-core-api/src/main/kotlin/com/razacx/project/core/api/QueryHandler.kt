package com.razacx.project.core.api

interface QueryHandler<INPUT: Query<OUTPUT>, OUTPUT> {

    fun handle(query: INPUT): OUTPUT
    fun getQueryType(): Class<INPUT>

}
