package io.swagger.model.patchDTOs

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.model.Event
import javax.validation.Valid

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