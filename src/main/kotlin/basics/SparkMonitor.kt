package basics

import org.springframework.scheduling.annotation.Scheduled

class SparkMonitor(val sparkAppsToMonitorConfiguration: SparkAppsToMonitorConfiguration,
                   val freq:Int) {

    @Scheduled(fixedDelayString = "#{sparkAppsMonitorConfiguration.monitoringFrequencySeconds *1000}")
    fun printConfiguration() {
        println("*** freq=$freq ***")
        println(sparkAppsToMonitorConfiguration)
    }
}