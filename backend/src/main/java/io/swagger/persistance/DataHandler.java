package io.swagger.persistance;

import io.swagger.model.Event;
import io.swagger.model.XMLModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.swagger.persistance.FileSaver.DATA_DIR;

public class DataHandler {

    private static final HashMap<String, io.swagger.model.Map> maps = new HashMap<>();
    private static final HashMap<String, Event> events = new HashMap<>();
    private static final Map<String, HashMap<String, ? extends XMLModel>> model = Map.of("maps", maps, "events", events);

    public static void init() throws IOException, JAXBException {
        JAXBContext mapContext = JAXBContext.newInstance(io.swagger.model.Map.class);
        Unmarshaller mapUnmarshaller = mapContext.createUnmarshaller();
        File folder = new File(DATA_DIR + "maps");
        File[] listOfMaps = folder.listFiles();
        if (null != listOfMaps) {
            for (File file : listOfMaps) {
                if (file.isFile()) {
                    io.swagger.model.Map m = (io.swagger.model.Map) mapUnmarshaller.unmarshal(file);
                    maps.put(m.getSerial(), m);
                }
            }
            System.out.println(maps);
        }
    }

}
