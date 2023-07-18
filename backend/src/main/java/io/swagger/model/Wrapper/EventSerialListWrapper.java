package io.swagger.model.Wrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "events")
public class EventSerialListWrapper{

    @XmlElement(name = "event")
    public Collection<String> events;

    public void setEvents(Collection<String> events) {
        this.events = events;
    }
}
