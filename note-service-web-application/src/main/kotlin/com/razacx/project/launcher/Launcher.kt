package com.razacx.project.launcher

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.razacx.project.adapter.rest.api.notesRoute
import com.razacx.project.adapter.sql.koinAdapterSqlModule
import com.razacx.project.common.util.date.DateProvider
import com.razacx.project.common.util.uuid.UUIDProvider
import com.razacx.project.core.api.koinCoreApiModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.JacksonConverter
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.Logger.slf4jLogger
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::main
    ).start(wait = true)
}

val koinRootModule = module {
    single { UUIDProvider() }
    single { DateProvider() }
}
val koinModules = listOf(
    koinCoreApiModule(),
    koinAdapterSqlModule(),
    koinRootModule
)

fun createObjectMapper(): ObjectMapper {
    return jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
}

fun Application.main() {
    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(createObjectMapper()))
    }
    install(Koin) {
        slf4jLogger()
        modules(koinModules)
    }
    routing {
        notesRoute()
    }
}
