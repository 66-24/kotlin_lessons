package basics

import com.google.common.collect.ImmutableList
import org.junit.Test
import kotlin.test.assertEquals

class SparkMonitorXmlToPojoMapperTest {
    @Test
    fun `should create valid SparkMonitor Pojo`() {
        val config =
                """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <sparkMonitorConfiguration>
                    <sparkJob>
                        <name>app1</name>
                        <resources>
                            <resource>app1.jar</resource>
                        </resources>
                    </sparkJob>
                    <sparkJob>
                        <name>app1</name>
                        <resources>
                            <resource>app2.jar</resource>
                        </resources>
                    </sparkJob>
                </sparkMonitorConfiguration>"""
        val xmlMapper = SparkMonitorXmlToPojoMapper()
        val sparkMonitor = xmlMapper.toPojo(config.reader())
        assertEquals(2, sparkMonitor.sparkJob.size)
        val sparkJobs = ImmutableList.copyOf(sparkMonitor.sparkJob)
        assertEquals(sparkJobs[0].name, "app1")
        assertEquals(sparkJobs[0].resources.size,1)
        assertEquals(sparkJobs[0].resources[0].resource,"app1.jar")

    }


    @Test
    fun `test marshalling`() {
        val sparkMonitor = SparkMonitorConfiguration()
        val sparkJobs = listOf(
                SparkJob("app1", listOf(Resource("app1.jar"))),
                SparkJob("app1", listOf(Resource("app2.jar")))
        )
        sparkMonitor.sparkJob.addAll(sparkJobs)

        val xml = SparkMonitorXmlToPojoMapper().toXml(sparkMonitor)
        println(xml)
    }
}