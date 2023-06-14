package com.dhbw.dndEvolved.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Event
 */
@Validated


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
        "serial",
        "name",
        "description",
        "location",
        "leadsToMapSerial"
})
@XmlRootElement(name = "event")
public class Event {

  @XmlElement(name = "serial")
  @JsonProperty("serial")
  private String serial;

  @XmlElement(name = "name")
  @JsonProperty("name")
  private String name;

  @XmlElement(name = "description")
  @JsonProperty("description")
  private String description;

  @XmlElement(name = "location")
  @JsonProperty("location")
  @Valid
  private Location location;

  @XmlElement(name = "leadsToMapSerial")
  @JsonProperty("leadsToMapSerial")
  private String leadsToMapSerial;

  /**
   * Get serial
   *
   * @return serial
   **/

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public Event name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   **/

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Event description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   *
   * @return description
   **/
  @Schema(description = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Event location(Location location) {
    this.location = location;
    return this;
  }

  @Valid
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Event leadsToMapSerial(String leadsToMapSerial) {
    this.leadsToMapSerial = leadsToMapSerial;
    return this;
  }

  /**
   * Get leadsToMapSerial
   *
   * @return leadsToMapSerial
   **/
  @Schema(description = "")

  public String getLeadsToMapSerial() {
    return leadsToMapSerial;
  }

  public void setLeadsToMapSerial(String leadsToMapSerial) {
    this.leadsToMapSerial = leadsToMapSerial;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.serial, event.serial) &&
            Objects.equals(this.name, event.name) &&
            Objects.equals(this.description, event.description) &&
            Objects.equals(this.location, event.location) &&
            Objects.equals(this.leadsToMapSerial, event.leadsToMapSerial);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serial, name, description, location, leadsToMapSerial);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");

    sb.append("    serial: ").append(toIndentedString(serial)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    leadsToMapSerial: ").append(toIndentedString(leadsToMapSerial)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
