package io.backvision.vlf.impl

import android.util.Log
import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLogProcessor

class AndroidLoggerProcessor : VlfLogProcessor {

    override fun onLogEvent(logEvent: VlfLogEvent) {


        logEvent.apply {

            fun tryLog(level: Int, block: () -> Unit) {
                if (Log.isLoggable(source, level)) block()
            }

            when (level) {

                VlfLogEvent.Level.VERBOSE -> tryLog(Log.VERBOSE) { Log.v(sourceAndTags, msg) }

                VlfLogEvent.Level.INFO -> tryLog(Log.INFO) { Log.i(sourceAndTags, msg) }

                VlfLogEvent.Level.DEBUG -> tryLog(Log.DEBUG) { Log.d(sourceAndTags, msg) }

                VlfLogEvent.Level.WARN -> tryLog(Log.WARN) { Log.w(sourceAndTags, msg) }

                VlfLogEvent.Level.ERROR -> tryLog(Log.ERROR) { Log.e(sourceAndTags, msg, ex) }
            }
        }
    }

    /*
    fun detectAndroidRuntime(): Boolean {
        try {
            android.util.Log.d("TAG", "MSG")
        } catch (ex: java.lang.RuntimeException) {
            return false
        }
        return true
    }
     */
}