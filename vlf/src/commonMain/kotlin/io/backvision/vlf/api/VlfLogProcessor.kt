package io.backvision.vlf.api

interface VlfLogProcessor {

    fun onLogEvent(logEvent: VlfLogEvent)

}