package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerProcessor


actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator { PrintLoggerProcessor() }
