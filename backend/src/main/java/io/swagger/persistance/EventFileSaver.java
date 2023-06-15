package io.swagger.persistance;

import io.swagger.model.Event;

import javax.xml.bind.JAXB;
import java.io.File;

public class EventFileSaver extends FileSaver<Event>{


    public static String getPathBySerial(String serial){
        return "";
    }

    @Override
    public void saveFile(Event model) {
        JAXB.marshal(model, getPath());
    }

    @Override
    public Event getFromFile(String filename) {
        return  JAXB.unmarshal(new File(getPath() + "/" + filename), Event.class);
    }

    @Override
    protected String getFolderName() {
        return "EventFiles";
    }
}
