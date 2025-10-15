package io.backvision.vlf.api

import io.backvision.vlf.impl.VlfLoggerImpl


fun VlfLogger.withTags(vararg tags: String): VlfLogger =
    Vlf.loggerFrom(source = source, tags = tags.toList() + (this as VlfLoggerImpl).additionalTags)


interface VlfLogger {

    val source: String

    fun verbose(vararg tags: String, msg: () -> String)
    fun verbose(msg: String) = verbose { msg }

    fun debug(vararg tags: String, msg: () -> String)
    fun debug(msg: String) = debug { msg }

    fun info(vararg tags: String, msg: () -> String)
    fun info(msg: String) = info { msg }

    fun warn(vararg tags: String, msg: () -> String)
    fun warn(msg: String) = warn { msg }

    fun error(ex: Throwable? = null, msg: String) = error(ex) { msg }
    fun error(ex: Throwable? = null, vararg tags: String, msg: () -> String)

}