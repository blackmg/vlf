package io.backvision.vlf.slf4j

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
        val logger: Logger = LoggerFactory.getLogger(logEvent.source)

        return logger.makeLoggingEventBuilder(logEvent.level.toSlf()).apply {
            setMessage(logEvent.msgProvider)
            logEvent.ex?.let { setCause(it) }
            logEvent.tags.takeIf { it.isNotEmpty() }?.onEach {
                addMarker(MarkerFactory.getMarker(it))
            }
        }.log()
    }
}