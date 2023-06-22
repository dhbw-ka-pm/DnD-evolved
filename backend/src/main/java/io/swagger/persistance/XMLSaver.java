package io.swagger.persistance;

import io.swagger.model.XMLModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;


public class XMLSaver<T extends XMLModel>{

    private final String folderName;

    private final static String XML_ENDING = ".xml";
    static final String DATA_DIR = "backend/persistence/xmlDocs/files/";

    public XMLSaver(String folderName) {
        this.folderName = folderName;
        File dir = new File(DATA_DIR + "/" + folderName);
        if (!dir.exists())
            dir.mkdirs();
    }

    public void saveFile(T model) throws JAXBException {
        File file = findFile(model.getSerial());
        JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        if(file.exists()){
            file.delete();
        }
        jaxbMarshaller.marshal(model, file);
    };

    public void removeFile(String serial){
        File file = findFile(serial);
        if(file.exists()){
            file.delete();
        }
    }

    private String getFolderName(){
        return folderName;
    };

    private File findFile(String serial){
        return new File(getFolderPath() +"/" + serial + XML_ENDING);
    }

    public static String getDirectoryPath(){
        return DATA_DIR;
    }
    public String getFolderPath() {
        return getDirectoryPath() + "/" + getFolderName();
    }

}
