package io.swagger.model.patchDTOs

import com.fasterxml.jackson.annotation.JsonProperty

data class PatchEvent(
        @field:JsonProperty("name")
        var name: String?,

        @field:JsonProperty("description")
        var description: String?,

        @field:JsonProperty("leadsToMapSerial")
        var leadsToMapSerial: String?)
