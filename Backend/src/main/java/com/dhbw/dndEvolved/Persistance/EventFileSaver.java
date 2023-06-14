package com.dhbw.dndEvolved.Persistance;

import com.dhbw.dndEvolved.model.Event;

public class EventFileSaver extends FileSaver<Event>{
    public static String getPathBySerial(String serial){
        return "";
    }


    @Override
    public void saveFile(Event model) {

    }

    @Override
    public Event getFromFile(String filename) {
        return null;
    }

    @Override
    protected String getFolderName() {
        return "EventFiles";
    }
}
