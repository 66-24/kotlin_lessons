package basics

import org.junit.Assert.assertEquals
import org.junit.Test
import javax.validation.Validation


class SparkMonitorConfigurationTest {
    @Test
    fun `should throw contraint violation`() {
        val config = SparkMonitorConfiguration()
        val factory = Validation.buildDefaultValidatorFactory()
        var validator = factory.getValidator()
        val violations = validator.validate(config)

        assertEquals(1, violations.size)
        println(violations.iterator().next().message)
    }

    @Test
    fun `should have atleast one resource`() {
        val config = SparkMonitorConfiguration()
        config.sparkJob.add(SparkJob("app1", listOf()))
        val factory = Validation.buildDefaultValidatorFactory()
        var validator = factory.getValidator()
        val violations = validator.validate(config)

            assertEquals(1, violations.size)
        println(violations.iterator().next().message)
    }
}

