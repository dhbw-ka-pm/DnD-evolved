package com.dhbw.dndEvolved.Persistance;

import com.dhbw.dndEvolved.model.Event;

import javax.xml.bind.JAXB;
import java.io.File;
import java.net.URI;

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
