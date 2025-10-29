package io.backvision.vlf.impl

import io.backvision.vlf.api.Vlf
import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLogProcessor

class PrintLoggerProcessor : VlfLogProcessor {

    private var installed: Boolean = false

    fun install() {
        if (!installed) {
            installed = true
            Vlf.installPlatform(PrintLoggerProcessor())
        }
    }

    override fun onLogEvent(logEvent: VlfLogEvent) {
        println(logEvent)
        logEvent.ex?.printStackTrace()
    }
}