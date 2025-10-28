package io.backvision.vlf.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.backvision.vlf.api.Vlf
import io.backvision.vlf.api.VlfLogger

@Composable
fun rememberVlfLogger(source: String): VlfLogger {

    println("source = ${source}")

    return remember(source) { Vlf.loggerFrom(source) }
}