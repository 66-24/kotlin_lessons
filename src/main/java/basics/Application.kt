package basics

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.scheduling.annotation.EnableScheduling




@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
open class Application(val config: SparkAppsToMonitor, @Value("\${sparkAppsToMonitor.version}")val version:String) : CommandLineRunner {

    override fun run(vararg p0: String?) {
        println(config)
        println("version: $version")
    }


//    @Bean
//    open fun mapSparkMonitorXmlToPojo(env: Environment, validator: Validator):SparkMonitorConfiguration {
//        val file = env.getProperty("configurationUrl")
//        val sparkMonitorConfiguration = SparkMonitorXmlToPojoMapper().toPojo(file)
////
////        val factory = Validation.buildDefaultValidatorFactory()
////        val validator1 = factory.validator
//        val violations
//                = validator.validate(sparkMonitorConfiguration)
//        if (violations.size > 0) {
//            throw SparkMonitorException(violations)
//        }
//        return sparkMonitorConfiguration
//    }
//
//    class SparkMonitorException(@NotNull constraintViolations: Set<ConstraintViolation<SparkMonitorConfiguration>>)
//        : RuntimeException(constraintViolations.toString())

//    @Bean
//    open fun sparkMonitor(sparkMonitorConfiguration: SparkMonitorConfiguration): SparkMonitor {
//        return SparkMonitor(sparkMonitorConfiguration)
//    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
//    Thread.currentThread().join(Duration.ofSeconds(10).toMillis())
}