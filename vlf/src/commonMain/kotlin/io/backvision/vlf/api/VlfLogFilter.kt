package io.backvision.vlf.api


fun interface VlfLogFilter {

    fun include(event: VlfLogEvent): Boolean

}