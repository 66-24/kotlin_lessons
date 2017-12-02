package basics

import org.json.XML
import org.springframework.beans.factory.config.YamlProcessor
import org.springframework.boot.yaml.SpringProfileDocumentMatcher
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.nodes.Tag
import org.yaml.snakeyaml.representer.Representer
import org.yaml.snakeyaml.resolver.Resolver
import java.io.ByteArrayInputStream
import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern


class XmlToMapConverter() {

    fun toMap(xmlPath: Path): Map<String, Any> {
        if (!xmlPath.toFile().isFile) {
            throw InvalidConfigurationException("${xmlPath.toAbsolutePath()} is not a file")
        }
        val xml = xmlPath.toFile().readText(charset("UTF-8"))
        val xmlToJson = XML.toJSONObject(xml)
        val json = xmlToJson.toString()
        val processor = Processor(InputStreamResource(ByteArrayInputStream(json.toByteArray())), null)
        val config = processor.process()

        return config
    }

    private class Processor internal constructor(resource: Resource, profile: String?) : YamlProcessor() {
        init {
            if (profile == null) {
                this.setMatchDefault(true)
                this.setDocumentMatchers(*arrayOf<DocumentMatcher>(SpringProfileDocumentMatcher()))
            } else {
                this.setMatchDefault(false)
                this.setDocumentMatchers(*arrayOf<DocumentMatcher>(SpringProfileDocumentMatcher(*arrayOf(profile))))
            }

            this.setResources(*arrayOf(resource))
        }

        override fun createYaml(): Yaml {
            return Yaml(StrictMapAppenderConstructor(), Representer(), DumperOptions(), object : Resolver() {
                override fun addImplicitResolver(tag: Tag, regexp: Pattern, first: String?) {
                    if (tag !== Tag.TIMESTAMP) {
                        super.addImplicitResolver(tag, regexp, first)
                    }
                }
            })
        }

        fun process(): Map<String, Any> {
            val result = LinkedHashMap<String, Any>()
            this.process { properties, map -> result.putAll(this@Processor.getFlattenedMap(map)) }
            return result
        }
    }

}