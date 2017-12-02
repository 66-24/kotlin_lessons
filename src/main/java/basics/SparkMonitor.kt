package basics

import org.springframework.scheduling.annotation.Scheduled

class SparkMonitor(val sparkMonitorConfig: SparkMonitorConfiguration) {
    @Scheduled(fixedDelayString = "#{spark}")
    fun printConfiguration() {
        println(sparkMonitorConfig)
    }
}