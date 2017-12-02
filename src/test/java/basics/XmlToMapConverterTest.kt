package basics

import org.junit.Test
import java.nio.file.Paths

class XmlToMapConverterTest {
    @Test
    fun `given valid xml should return Map`() {
        val toMap = XmlToMapConverter().toMap(Paths.get("/home/srini/Development/Projects/kotlin_lessons/src/test/resources/spark_jobs.xml"))
        println(toMap)
    }
}

