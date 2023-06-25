package io.swagger.model.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Location;

import java.util.List;

public class MapEvent {
    @JsonProperty("serial")
    String serial;

    @JsonProperty("location")
    Location location;

    public static MapEvent create(String serial, Location location) {
        MapEvent event = new MapEvent();
        event.setSerial(serial);
        event.setLocation(location);
        return event;
    }

    public MapEvent(){}

    public String getSerial() {
        return serial;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Location getLocation() {
        return location;
    }

    public boolean exists(List<MapEvent> list){
        return list.contains(getSerial());
    }
}
