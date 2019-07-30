package com.razacx.project

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.razacx.project.common.UnitTest
import com.razacx.project.launcher.main
import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.testing.*

open class IntegrationTest : UnitTest() {

//    @AfterEach
//    internal fun tearDown() {
//        transaction {
//            SchemaUtils.drop(*tables.toTypedArray())
//        }
//    }

    // Can't be private since we can't inline our fromJson function otherwise
    protected val objectMapper = jacksonObjectMapper()

    protected fun <R> integrationTest(test: TestApplicationEngine.() -> R): R {
//        beanDefinitions = createBeanDefinitions()
//        beanDefinitions!!.singleBy<DatabaseConnector, DatabaseConnectorTestImpl>(override = true)
//        beanDefinitions!!.singleBy<DateProvider, DateProviderTestImpl>(override = true)
        return withTestApplication(Application::main, test)
    }

    protected fun post(application: TestApplicationEngine, route: String, body: String): TestApplicationResponse {
        return with(application.handleRequest(HttpMethod.Post, route) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }) { response }
    }

    protected fun get(application: TestApplicationEngine, route: String): TestApplicationResponse {
        return with(application.handleRequest(HttpMethod.Get, route)) { response }
    }

    protected fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    protected inline fun <reified R> fromJson(json: String): R {
        return objectMapper.readValue(json)
    }

}
