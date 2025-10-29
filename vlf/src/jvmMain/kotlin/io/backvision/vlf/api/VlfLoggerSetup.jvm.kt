package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerBackend

actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator { load() }


fun load(): VlfLogProcessor {
    println("<top>.load")
    val clazz = Class.forName("io.backvision.vlf.jvm.VlfServiceLocatorImpl")
    println("clazz = ${clazz}")

    if (clazz != null) {
        val instance = clazz.newInstance() as? VlfServiceLocator
        println("instance = ${instance}")
        if (instance != null) {
            return instance.loadPlatformDefault()
        }
    }
    return PrintLoggerBackend()

}