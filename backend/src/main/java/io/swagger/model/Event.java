package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
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
        "leadsToMapSerial",
        "leadsToChildMapSerial"
})
@XmlRootElement(name = "Event")
public class Event implements XMLModel {

  @XmlElement(name = "serial")
  @JsonProperty("serial")
  private String serial;

  @XmlElement(name = "name")
  @JsonProperty("name")
  private String name;

  @XmlElement(name = "description")
  @JsonProperty("description")
  private String description;


  @XmlElement(name = "leadsToMapSerial")
  @JsonProperty("leadsToMapSerial")
  private String leadsToMapSerial;


  @XmlElement(name = "leadsToChildMapSerial")
  @JsonProperty("leadsToChildMapSerial")
  private String leadsToChildMapSerial;

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

  public String getLeadsToChildMapSerial() {
    return leadsToChildMapSerial;
  }

  public void setLeadsToChildMapSerial(String leadsToChildMapSerial) {
    this.leadsToChildMapSerial = leadsToChildMapSerial;
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
}
