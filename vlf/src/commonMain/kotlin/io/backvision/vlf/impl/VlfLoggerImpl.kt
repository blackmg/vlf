package io.backvision.vlf.impl

import io.backvision.vlf.api.Vlf
import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLogEvent.Level.*
import io.backvision.vlf.api.VlfLogger

open class VlfLoggerImpl(override val source: String, tags: List<String>) : VlfLogger {

    val additionalTags: Set<String> = tags.toSet()

    open fun logEvent(
        level: VlfLogEvent.Level,
        tags: List<String> = emptyList(),
        msg: () -> String,
        ex: Throwable? = null
    ) =
        VlfLogEvent(source, level, tags = tags, msgProvider = msg, ex = ex).also { log(it) }

    fun allTags(vararg tags: String): List<String> = (tags.toList() + additionalTags).sorted()

    override fun verbose(vararg tags: String, msg: () -> String) {
        logEvent(VERBOSE, tags = allTags(*tags), msg = msg)
    }

    override fun debug(vararg tags: String, msg: () -> String) {
        logEvent(DEBUG, allTags(*tags), msg = msg)
    }

    override fun info(vararg tags: String, msg: () -> String) {
        logEvent(INFO, allTags(*tags), msg = msg)
    }

    override fun warn(vararg tags: String, msg: () -> String) {
        logEvent(WARN, allTags(*tags), msg = msg)
    }

    override fun error(ex: Throwable?, vararg tags: String, msg: () -> String) {
        logEvent(ERROR, allTags(*tags), msg = msg, ex = ex)
    }

    open fun log(logEvent: VlfLogEvent) {
        Vlf.send(logEvent)
    }
}
