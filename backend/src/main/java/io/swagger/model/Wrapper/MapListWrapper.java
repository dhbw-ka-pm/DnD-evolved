package io.swagger.model.Wrapper;

import io.swagger.model.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "maps")
public class MapListWrapper {
    private List<Map> maps;

    @XmlElement(name="map") // This names the XML elements as "map"
    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }
}
