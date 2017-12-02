package basics

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "sparkAppsToMonitor")
//sparkAppsToMonitor.sparkAppLaunchConfiguration[0].resource[0]
open class SparkAppsToMonitor {
    var sparkAppLaunchConfiguration: List<SparkAppLaunchConfiguration> = ArrayList()

    override fun toString(): String {
        return "SparkAppsToMonitor(sparkAppLaunchConfiguration=$sparkAppLaunchConfiguration)"
    }

    open class SparkAppLaunchConfiguration {
        var name: String = ""
        var resource: List<String> = ArrayList()
        override fun toString(): String {
            return "SparkAppLaunchConfiguration(name='$name', resource=$resource)"
        }
    }

}


