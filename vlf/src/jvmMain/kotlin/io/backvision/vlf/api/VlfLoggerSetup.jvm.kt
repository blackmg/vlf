package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerProcessor

actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator { load() }


fun load(): VlfLogProcessor = (Class.forName("io.backvision.vlf.jvm.VlfServiceLocatorImpl")
    .newInstance() as? VlfServiceLocator)?.loadPlatformDefault() ?: PrintLoggerProcessor()

