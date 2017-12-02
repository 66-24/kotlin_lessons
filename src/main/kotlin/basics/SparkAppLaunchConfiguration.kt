package basics

import lombok.ToString
import org.springframework.stereotype.Component
import javax.validation.constraints.NotEmpty

@Component
@ToString
open class SparkAppLaunchConfiguration {
    @NotEmpty
    var name: String = ""
    @NotEmpty
    var resource: List<String> = ArrayList()
    override fun toString(): String {
        return "SparkAppLaunchConfiguration(name='$name', resource=$resource)"
    }
}
