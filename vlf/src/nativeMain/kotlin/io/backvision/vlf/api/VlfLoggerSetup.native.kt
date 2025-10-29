package io.backvision.vlf.api

import io.backvision.vlf.impl.PrintLoggerBackend


actual val platformServiceLocator: VlfServiceLocator = VlfServiceLocator { PrintLoggerBackend() }
