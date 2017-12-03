package basics

import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringApplicationRunListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import java.nio.file.Paths

class XmlPropertySourceLoader(val app: SpringApplication, val args: Array<String>) : SpringApplicationRunListener {
    override fun finished(p0: ConfigurableApplicationContext?, p1: Throwable?) {
        println("Application finished")
    }

    override fun environmentPrepared(env: ConfigurableEnvironment?) {
        val xmlPath = Paths.get(env?.getProperty("configurationUrl"))
        if(! xmlPath.toFile().isFile) {
            throw InvalidConfigurationException("${xmlPath.toAbsolutePath().fileName} is not a file")
        }
        val configAsMap = XmlToMapConverter().toMap(xmlPath)
        val mapPropertySource = MapPropertySource("${xmlPath.fileName}", configAsMap)
        println("Loaded Xml configuration: ${xmlPath.toAbsolutePath()}")
        env?.propertySources?.addLast(mapPropertySource)
        println(configAsMap)
    }

    override fun contextPrepared(p0: ConfigurableApplicationContext?) {
        println("Application context prepared")
    }

    override fun contextLoaded(p0: ConfigurableApplicationContext?) {
        println("Application context loaded")
    }

    override fun starting() {
        println("Starting application")
    }

}