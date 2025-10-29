package io.backvision.vlf.jvm

import io.backvision.vlf.api.VlfServiceLocator
import io.backvision.vlf.slf4j.Slf4jLogProcessor

class VlfServiceLocatorImpl : VlfServiceLocator {

    override fun loadPlatformDefault(): Slf4jLogProcessor {
        println("VlfServiceLocatorImpl.loadPlatformDefault - jvm")
        return Slf4jLogProcessor()
    }
}