package io.backvision.vlf.api

import io.backvision.vlf.api.Vlf.loggerFrom
import io.backvision.vlf.api.Vlf.loggerSource
import io.backvision.vlf.impl.PrintLoggerBackend
import io.backvision.vlf.impl.VlfLoggerImpl
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlin.reflect.KClass

expect val platformServiceLocator: VlfServiceLocator

fun interface VlfServiceLocator {

    fun loadPlatformDefault(): VlfLogProcessor
}


fun <R : Any> R.vlfLogger(vararg tags: String): Lazy<VlfLogger> =
    lazy { loggerFrom(source = this::class.loggerSource(), tags = tags.toList()) }

object Vlf {

    private val lock = reentrantLock()
    private val processors = mutableListOf<VlfLogProcessor>()
    private val filters = mutableListOf<VlfLogFilter>()

    var overriddenPlatform: VlfLogProcessor? = null

    val loadedPlatformProcessor: VlfLogProcessor by lazy { platformServiceLocator.loadPlatformDefault() }

    val platform: VlfLogProcessor
        get() = overriddenPlatform ?: loadedPlatformProcessor

    fun KClass<*>.loggerSource(): String = simpleName.toString()

    fun loggerFrom(source: String, tags: List<String> = emptyList()): VlfLogger = VlfLoggerImpl(source, tags)

    private fun withLock(block: () -> Unit) = lock.withLock(block)

    fun installSystemOutPlatform() = withLock {
        if (overriddenPlatform !is PrintLoggerBackend) {
            overriddenPlatform = PrintLoggerBackend()
        }
    }

    fun installPlatform(processor: VlfLogProcessor) = withLock {
        overriddenPlatform = processor
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


