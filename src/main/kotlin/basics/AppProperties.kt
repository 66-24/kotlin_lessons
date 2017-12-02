package basics

import org.hibernate.validator.constraints.Range
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Component("sparkAppsMonitorConfiguration")
@ConfigurationProperties(prefix = "sparkAppsMonitorConfiguration")
@Validated
//sparkAppsMonitorConfiguration.sparkAppLaunchConfiguration[0].resource[0]
open class SparkAppsToMonitorConfiguration {
    @NotEmpty
    open  var sparkAppLaunchConfiguration: List<SparkAppLaunchConfiguration> = ArrayList()
    @NotEmpty
    open var version : String = ""

    @Range(min=1, max=86400)
    open var monitoringFrequencySeconds:Int = 0

    override fun toString(): String {
        return "SparkAppsToMonitorConfiguration(sparkAppLaunchConfiguration=$sparkAppLaunchConfiguration, version='$version', monitoringFrequencySeconds=$monitoringFrequencySeconds)"
    }


}