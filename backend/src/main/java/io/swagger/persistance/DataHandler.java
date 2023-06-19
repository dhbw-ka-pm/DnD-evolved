package io.swagger.persistance;

import io.swagger.model.Event;
import io.swagger.model.XMLModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataHandler {

    private final HashMap<String, io.swagger.model.Map> maps = new HashMap<>();
    private final HashMap<String, Event> events = new HashMap<>();
    private final Map<String, HashMap<String, ? extends XMLModel>> model = Map.of("maps", maps, "events", events);

    private final XMLSaver<Event> eventXMLSaver;
    private final XMLSaver<io.swagger.model.Map> mapXMLSaver;


    private DataHandler(){
        eventXMLSaver = new XMLSaver<>("events");
        mapXMLSaver = new XMLSaver<>("maps");
        try {
            init();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public static DataHandler createDataHandler() {
        return new DataHandler();
    }

    public void init() throws JAXBException {
        JAXBContext mapContext = JAXBContext.newInstance(io.swagger.model.Map.class);
        Unmarshaller mapUnmarshaller = mapContext.createUnmarshaller();
        File mapsFolder = new File(mapXMLSaver.getFolderPath());
        File[] listOfMaps = mapsFolder.listFiles();
        if (null != listOfMaps) {
            for (File file : listOfMaps) {
                if (file.isFile()) {
                    io.swagger.model.Map m = (io.swagger.model.Map) mapUnmarshaller.unmarshal(file);
                    maps.put(m.getSerial(), m);
                }
            }
            System.out.println(maps);
        }
        JAXBContext eventContext = JAXBContext.newInstance(Event.class);
        Unmarshaller eventUnmarshaller = eventContext.createUnmarshaller();
        File eventsFolder = new File(eventXMLSaver.getFolderPath());
        File[] listOfEvents = eventsFolder.listFiles();
        if (null != listOfEvents) {
            for (File file : listOfEvents) {
                if (file.isFile()) {
                    Event e = (Event) eventUnmarshaller.unmarshal(file);
                    events.put(e.getSerial(), e);
                }
            }
            System.out.println(events);
        }
    }

    public void putMap(io.swagger.model.Map map) throws JAXBException {
        mapXMLSaver.saveFile(map);
        maps.put(map.getSerial(), map);
    }

    public void putEvent(Event event) throws JAXBException {
        eventXMLSaver.saveFile(event);
        events.put(event.getSerial(), event);
    }

    public void saveImage(io.swagger.model.Map map){
        File f = null;
    }


}
