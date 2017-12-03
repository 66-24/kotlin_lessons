package basics

import org.junit.Test

class SparkAppsToMonitorConfigurationTest {
    @Test
    fun test() {
        val sparkAppsToMonitor = SparkAppsToMonitorConfiguration()
        sparkAppsToMonitor.sparkAppLaunchConfiguration = ArrayList()

        val sparkAppsToMonitor1 = SparkAppsToMonitorConfiguration()
        sparkAppsToMonitor1.sparkAppLaunchConfiguration
    }
}