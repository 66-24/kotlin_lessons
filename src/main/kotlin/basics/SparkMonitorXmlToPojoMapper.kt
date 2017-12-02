package basics

import java.io.File
import java.io.FileReader
import java.io.Reader
import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller

class SparkMonitorXmlToPojoMapper {
    private val unmarshaller: Unmarshaller
    private val marshaller: Marshaller

    init {
        val jaxbContext = JAXBContext.newInstance(SparkMonitorConfiguration::class.java)
        unmarshaller = jaxbContext.createUnmarshaller()

        marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    }

    fun toPojo(filename: String): SparkMonitorConfiguration {
        return toPojo(FileReader(File(filename)))
    }

    fun toPojo(reader: Reader): SparkMonitorConfiguration {
        return unmarshaller.unmarshal(reader) as SparkMonitorConfiguration
    }

    fun toXml(sparkMonitorConfiguration: SparkMonitorConfiguration): String {
        val stringWriter = StringWriter()
        stringWriter.use {
            marshaller.marshal(sparkMonitorConfiguration, it)
        }
        return stringWriter.toString()
    }
}