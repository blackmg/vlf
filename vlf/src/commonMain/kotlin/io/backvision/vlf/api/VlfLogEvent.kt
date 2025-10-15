package io.backvision.vlf.api


// TODO: Dsl api?
data class VlfLogEvent(
    val source: String,
    val level: Level,
    val msgProvider: () -> String,
    val ex: Throwable? = null,
    val tags: List<String>,
) {
    enum class Level { VERBOSE, DEBUG, INFO, WARN, ERROR }

    val msg: String by lazy { msgProvider() }

    private val toString: String by lazy {
        buildString {
            append(level)
            append(":")
            append(sourceAndTags)
            append(" ")
            append(msg)
            if (ex != null) {
                append(" - ")
                append(ex.message)
            }
        }
    }

    val sourceAndTags: String by lazy {
        buildString {
            append("$source:")
            if (tags.isNotEmpty()) {
                append("[")
                append(tags.joinToString(","))
                append("]")
            }
        }
    }

    override fun toString(): String = toString
}