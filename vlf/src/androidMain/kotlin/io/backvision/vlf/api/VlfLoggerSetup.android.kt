package io.backvision.vlf.api

import io.backvision.vlf.impl.AndroidLoggerProcessor

actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator { AndroidLoggerProcessor() }
