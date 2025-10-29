package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerProcessor

actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator {
    try {
        (Class.forName("io.backvision.vlf.jvm.VlfServiceLocatorImpl")
            .getDeclaredConstructor().newInstance() as? VlfServiceLocator)?.loadPlatformDefault()
            ?: PrintLoggerProcessor()
    } catch (notFound: Exception) {
        PrintLoggerProcessor()
    }
}


