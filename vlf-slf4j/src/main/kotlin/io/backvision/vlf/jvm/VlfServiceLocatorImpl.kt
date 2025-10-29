package io.backvision.vlf.jvm

import io.backvision.vlf.api.VlfServiceLocator
import io.backvision.vlf.slf4j.Slf4jLogProcessor

@Suppress("unused")
class VlfServiceLocatorImpl : VlfServiceLocator {

    override fun loadPlatformDefault(): Slf4jLogProcessor {
        return Slf4jLogProcessor()
    }
}