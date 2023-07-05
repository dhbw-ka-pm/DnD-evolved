package io.swagger.model.patchDTOs

import com.fasterxml.jackson.annotation.JsonProperty
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "Map")
data class PatchMap(
        @field:JsonProperty("name")
        var name: String? = null,

        @field:JsonProperty("description")
        var description: String? = null,

        @field:JsonProperty("sizeX")
        var sizeX: Int? = null,

        @field:JsonProperty("sizeY")
        var sizeY: Int? = null,


)