package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Utils.MapEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Validated
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Map", propOrder = {
        "serial",
        "name",
        "description",
        "imagePath",
        "sizeX",
        "sizeY",
        "events"
})
@XmlRootElement(name = "Map")
public class Map implements XMLModel {
  @XmlElement(name = "Serial")
  @JsonProperty("serial")
  private String serial = null;

  @XmlElement(name = "Name")
  @JsonProperty("name")
  private String name = null;

  @XmlElement(name = "Description")
  @JsonProperty("description")
  private String description = null;

  @XmlElement(name = "ImagePath")
  @JsonProperty("imagePath")
  private String imagePath = null;

  @XmlElement(name = "SizeX")
  @JsonProperty("sizeX")
  private Integer sizeX = null;

  @XmlElement(name = "SizeY")
  @JsonProperty("sizeY")
  private Integer sizeY = null;

  @XmlElementWrapper(name = "Event")
  @JsonProperty("events")
  @Valid
  private List<MapEvent> events = null;

  // Getters and setters omitted for brevity

  // Equals, hashCode, and toString methods omitted for brevity
  public Map serial(String serial) {
    this.serial = serial;
    return this;
  }

  /**
   * Get serial
   *
   * @return serial
   **/
  @Schema(description = "")

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public Map name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   **/
  @Schema(description = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map description(String description) {
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

  public Map imagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }

  /**
   * Get imagePath
   *
   * @return imagePath
   **/
  @Schema(description = "")

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Map sizeX(Integer sizeX) {
    this.sizeX = sizeX;
    return this;
  }

  /**
   * Get sizeX
   *
   * @return sizeX
   **/
  @Schema(description = "")

  public Integer getSizeX() {
    return sizeX;
  }

  public void setSizeX(Integer sizeX) {
    this.sizeX = sizeX;
  }

  public Map sizeY(Integer sizeY) {
    this.sizeY = sizeY;
    return this;
  }

  /**
   * Get sizeY
   *
   * @return sizeY
   **/
  @Schema(description = "")

  public Integer getSizeY() {
    return sizeY;
  }

  public void setSizeY(Integer sizeY) {
    this.sizeY = sizeY;
  }

  public List<MapEvent> events( List<MapEvent> events) {
    this.events = events;
    return this.getEvents();
  }

  public void addEventsItem(String eventSerial, Location location) {
    if (this.events == null) {
      this.events = new ArrayList<>();
    }
    this.events.add(MapEvent.create(eventSerial, location));
  }

  /**
   * Get events
   *
   * @return events
   **/
  @Schema(description = "")
  @Valid
  public List<MapEvent> getEvents() {
    return events;
  }

  public void setEvents(List<MapEvent> events) {
    this.events = events;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Map map = (Map) o;
    return Objects.equals(this.serial, map.serial) &&
            Objects.equals(this.name, map.name) &&
            Objects.equals(this.description, map.description) &&
            Objects.equals(this.imagePath, map.imagePath) &&
            Objects.equals(this.sizeX, map.sizeX) &&
            Objects.equals(this.sizeY, map.sizeY) &&
            Objects.equals(this.events, map.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serial, name, description, imagePath, sizeX, sizeY, events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Map {\n");

    sb.append("    serial: ").append(toIndentedString(serial)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    imagePath: ").append(toIndentedString(imagePath)).append("\n");
    sb.append("    sizeX: ").append(toIndentedString(sizeX)).append("\n");
    sb.append("    sizeY: ").append(toIndentedString(sizeY)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
