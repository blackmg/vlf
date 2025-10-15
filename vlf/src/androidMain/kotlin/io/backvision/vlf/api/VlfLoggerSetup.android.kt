package io.backvision.vlf.api

import io.backvision.vlf.impl.AndroidLoggerProcessor

actual val platformLoggerProcessor: VlfLogProcessor = AndroidLoggerProcessor()
