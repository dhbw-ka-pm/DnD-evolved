package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Validated
data class Map2(
    @field:JsonProperty("serial")
    var serial: String,

    @field:JsonProperty("name")
    var name: String,

    @field:JsonProperty("description")
    var description: String? = null,

    @field:JsonProperty("imagePath")
    var imagePath: String? = null,

    @field:JsonProperty("sizeX")
    var sizeX: Int,

    @field:JsonProperty("sizeY")
    var sizeY: Int?,

    @field:JsonProperty("events")
    @field:Valid
    var events: HashMap<String, Location>? = null
) : XMLModel {
    fun addEventsItem(eventSerial: String, location: Location) {
        if (events == null) {
            events = HashMap()
        }
        events!!.put(eventSerial, location)
    }

    override fun getSerial(): String {
        return serial;
    }

    override fun setSerial(serial: String) {
        this.serial = serial;
    }
}
