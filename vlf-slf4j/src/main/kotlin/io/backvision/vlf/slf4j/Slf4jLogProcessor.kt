package io.backvision.vlf.io.backvision.vlf.slf4j

import io.backvision.vlf.api.Vlf
import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLogProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MarkerFactory
import org.slf4j.event.Level

fun VlfLogEvent.Level.toSlf(): Level = when (this) {
    VlfLogEvent.Level.VERBOSE -> Level.TRACE
    VlfLogEvent.Level.DEBUG -> Level.DEBUG
    VlfLogEvent.Level.INFO -> Level.INFO
    VlfLogEvent.Level.WARN -> Level.WARN
    VlfLogEvent.Level.ERROR -> Level.ERROR
}

class Slf4jLogProcessor : VlfLogProcessor {

    companion object {

        private var installed: Boolean = false

        fun install() {
            if (!installed) {
                installed = true
                Vlf.installPlatform(Slf4jLogProcessor())
            }
        }
    }

    @Suppress("CheckResult")
    override fun onLogEvent(logEvent: VlfLogEvent) {
//        println("Slf4jLogProcessor.onLogEvent ${logEvent}")
        // returns NOPLogger if nothing found
        val logger: Logger = LoggerFactory.getLogger(logEvent.source)

        val builder = logger.makeLoggingEventBuilder(logEvent.level.toSlf())
            .setMessage(logEvent.msgProvider)

        logEvent.ex?.let { builder.setCause(it) }
        logEvent.tags.takeIf { it.isNotEmpty() }?.onEach {
            val marker = MarkerFactory.getMarker(it);
            builder.addMarker(marker)
        }

        builder.log()
    }
}