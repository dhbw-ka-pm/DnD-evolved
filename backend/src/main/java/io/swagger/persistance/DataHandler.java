package io.swagger.persistance;

import io.swagger.model.Event;
import io.swagger.model.XMLModel;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

@Service
public class DataHandler {


    public static boolean FILEPERSISTANCE = true;

    private final HashMap<String, io.swagger.model.Map> maps = new HashMap<>();
    private final HashMap<String, Event> events = new HashMap<>();
    private final Map<String, HashMap<String, ? extends XMLModel>> model = Map.of("maps", maps, "events", events);

    private final XMLSaver<Event> eventXMLSaver;
    private final XMLSaver<io.swagger.model.Map> mapXMLSaver;




    @Autowired
    private DataHandler(ImageSaver imageSaver){

        eventXMLSaver = new XMLSaver<>("events");
        mapXMLSaver = new XMLSaver<>("maps");
        try {
            init();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
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
        if(FILEPERSISTANCE)
            mapXMLSaver.saveFile(map);
        maps.put(map.getSerial(), map);
    }

    public void putEvent(Event event) throws JAXBException {
        if(FILEPERSISTANCE)
            eventXMLSaver.saveFile(event);
        events.put(event.getSerial(), event);
    }

    public Event getEvent(String serial) throws SerialNotFoundException {
        if (!events.containsKey(serial))
            throw new SerialNotFoundException("this Serial does not lead to any existent Event.");
        else
            return events.get(serial);
    }

    public static void checkContains(Map<String, ? extends XMLModel> map, String serial) throws SerialNotFoundException {
        if (!map.containsKey(serial))
            throw new SerialNotFoundException("this Serial does not lead to any existent File");
    }

    public void removeMap(String serial) throws SerialNotFoundException {
        checkContains(maps, serial);
        maps.remove(serial);
        mapXMLSaver.removeFile(serial);
    }


    public io.swagger.model.Map getMap(String serial) throws SerialNotFoundException {
        checkContains(maps, serial);
        return maps.get(serial);
    }

    public void saveImage(io.swagger.model.Map map) {
    }

    public void updateMap(String serial) throws JAXBException {
        mapXMLSaver.saveFile(maps.get(serial));
    }

    public void removeEvent(String serial) throws SerialNotFoundException {
        checkContains(events, serial);
        events.remove(serial);
        eventXMLSaver.removeFile(serial);

    }


    public List<io.swagger.model.Map> getAllMaps() {
        return List.copyOf(maps.values());
    }


    public static class SerialNotFoundException extends Exception {
        public SerialNotFoundException(String serial) {
            super(serial);
        }


    };

    public static void copyNonNullProperties(
            Object source, Object target
    ) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (
                        sourcePd != null
                                && sourcePd.getReadMethod() != null
                ) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (
                                !Modifier.isPublic(
                                        readMethod.getDeclaringClass()
                                                .getModifiers())
                        ) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // Ignore properties with null values.
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (
                                    !Modifier.isPublic(
                                            writeMethod.getDeclaringClass()
                                                    .getModifiers())
                            ) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException(
                                "Could not copy properties from source to target", ex
                        );
                    }
                }
            }
        }
    }
}
