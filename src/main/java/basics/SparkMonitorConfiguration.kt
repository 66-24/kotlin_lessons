package basics

import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
open class SparkMonitorConfiguration {
    @XmlElement
    @Valid
    val sparkJob: MutableList<@NotNull SparkJob> = mutableListOf()

    override fun toString(): String {
        return sparkJob.toString()
    }
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/*
https://stackoverflow.com/questions/35847763/kotlin-data-class-bean-validation-jsr-303
 */
open class SparkJob(@XmlElement @NotEmpty val name: String,
               @get:Valid @get:Size(min=1) @XmlElement val resources: List<@NotNull Resource>) {
    constructor() : this("", mutableListOf())

    override fun toString(): String {
        return "SparkJob(name='$name', resources=$resources)"
    }

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
open class Resource(@get:Size(min=1) @XmlElement val resource: String){
    constructor():this("")

    override fun toString(): String {
        return "Resource(resource='$resource')"
    }

}