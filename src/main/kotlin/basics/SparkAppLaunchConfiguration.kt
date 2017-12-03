package basics

import org.springframework.stereotype.Component
import javax.validation.constraints.NotEmpty

@Component
open class SparkAppLaunchConfiguration {
    @NotEmpty
    var name: String = ""
    @NotEmpty
    var resource: List<String> = ArrayList()
    override fun toString(): String {
        return "SparkAppLaunchConfiguration(name='$name', resource=$resource)"
    }
}
