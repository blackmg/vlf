package io.backvision.vlf.impl

import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLogProcessor

class PrintLoggerBackend : VlfLogProcessor {

    override fun onLogEvent(logEvent: VlfLogEvent) {
        println(logEvent)
        logEvent.ex?.printStackTrace()
    }
}