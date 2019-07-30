package com.razacx.project.adapter.rest.api

import com.razacx.project.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloWorldRouteTestKt: IntegrationTest() {

    @Test
    internal fun `Hello world route returns hello world`() = integrationTest {
        val response = get(this, "/")
        assertThat(response.content).isEqualTo("Hello world!")
        Unit
    }

}
