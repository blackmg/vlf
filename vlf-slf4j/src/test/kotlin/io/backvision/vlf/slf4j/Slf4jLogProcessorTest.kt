package io.backvision.vlf.slf4j

import io.backvision.vlf.api.vlfLogger
import kotlin.test.Test

class TestSlf4j {

    val log by vlfLogger()

    fun test() {
        log.info { "Some info" }
        log.debug() { "Some debug" }
    }

    fun testMarkers() {
        log.info("TAG") { "Some info" }
        log.debug("OTHER", "TAG") { "Some debug" }
    }
}

class Slf4jLogProcessorTest {

    @Test
    fun basics() {
        val testSlf4j = TestSlf4j()

        testSlf4j.test()
    }

    @Test
    fun markers() {
        val testSlf4j = TestSlf4j()

        testSlf4j.testMarkers()
    }

}