package io.swagger.model.patchDTOs

import com.fasterxml.jackson.annotation.JsonProperty
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "Event")

data class PatchEvent(
        @field:JsonProperty("name")
        var name: String? = null,

        @field:JsonProperty("description")
        var description: String? = null,

        @field:JsonProperty("leadsToMapSerial")
        var leadsToMapSerial: String? = null)
