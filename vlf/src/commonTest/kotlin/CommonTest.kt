import io.backvision.vlf.api.VlfLogEvent
import io.backvision.vlf.api.VlfLoggerSetup
import io.backvision.vlf.api.vlfLogger
import io.backvision.vlf.api.withTags
import kotlin.test.Test

class CommonTest {

    val log by vlfLogger()

    init {
        VlfLoggerSetup.installSystemOutPlatform()
    }

    @Test
    fun basics() {
        VlfLoggerSetup.installFilter { it.level != VlfLogEvent.Level.DEBUG }

        log.info { "Some Info" }
        log.debug { "Some Debug" }

        VlfLoggerSetup.clearFilters()
    }

    @Test
    fun tags() {
        log.info { "Some Info with no tag" }
        log.info("ATAG") { "Some Info with tag" }
        log.info("ATAG", "BTAG") { "Some Info with tags" }
        log.debug { "Some Debug" }
    }

    @Test
    fun tagsPerLog() {
        val parent = log.withTags("PARENT")
        val log = parent.withTags("ADDED")
        log.info { "Some Info with no tag" }
        log.info("ATAG") { "Some Info from parent with tag" }
        log.info("ATAG", "BTAG") { "Some Info with tags" }
        log.info("BTAG", "ATAG") { "Other way around tags" }

        log.debug { "Some Debug" }
    }
}