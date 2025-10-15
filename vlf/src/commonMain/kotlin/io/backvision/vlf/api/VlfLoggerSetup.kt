package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerBackend
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock

expect val platformLoggerProcessor: VlfLogProcessor

object VlfLoggerSetup {

    private val lock = reentrantLock()
    private val processors = mutableListOf<VlfLogProcessor>()
    private val filters = mutableListOf<VlfLogFilter>()

    var overriddenPlatform: VlfLogProcessor? = null

    val platform: VlfLogProcessor
        get() = overriddenPlatform ?: platformLoggerProcessor

    private fun withLock(block: () -> Unit) = lock.withLock(block)

    fun installSystemOutPlatform() = withLock {
        if (overriddenPlatform !is PrintLoggerBackend) {
            overriddenPlatform = PrintLoggerBackend()
        }
    }

    fun installProcessor(processor: VlfLogProcessor) = withLock {
        processors.add(processor)
    }

    fun installFilter(filter: VlfLogFilter) = withLock {
        filters.add(filter)
    }

    fun clearFilters() = withLock {
        filters.clear()
    }

    fun send(logEvent: VlfLogEvent) {
        if (filters.all { it.include(logEvent) }) {
            platform.onLogEvent(logEvent)
            processors.forEach { it.onLogEvent(logEvent) }
        }
    }


}