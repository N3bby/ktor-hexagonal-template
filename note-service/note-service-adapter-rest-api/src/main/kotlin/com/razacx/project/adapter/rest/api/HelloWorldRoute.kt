package com.razacx.project.adapter.rest.api

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.helloWorldRoute() {
    route("/") {
        get {
            call.respond("Hello world!")
        }
    }
}
