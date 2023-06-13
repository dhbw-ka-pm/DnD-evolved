package io.swagger.model;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
        "serial",
        "name",
        "description",
        "location",
        "leadsToMapSerial"
})
@Validated
public class Event {

  @XmlElement(name = "serial")
  private String serial;

  @XmlElement(name = "name")
  private String name;

  @XmlElement(name = "description")
  private String description;

  @XmlElement(name = "location")
  @Valid
  private Location location;

  @XmlElement(name = "leadsToMapSerial")
  private String leadsToMapSerial;

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getLeadsToMapSerial() {
    return leadsToMapSerial;
  }

  public void setLeadsToMapSerial(String leadsToMapSerial) {
    this.leadsToMapSerial = leadsToMapSerial;
  }

  // Implement equals(), hashCode(), and toString() methods if needed
}
