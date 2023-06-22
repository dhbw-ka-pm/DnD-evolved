package io.swagger.persistance;

import io.swagger.model.XMLModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


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
        File file = new File(getFolderPath() +"/" + model.getSerial() + XML_ENDING);
        JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        if(file.exists()){
            file.delete();
        }
        jaxbMarshaller.marshal(model, file);
    };

    private String getFolderName(){
        return folderName;
    };

    public static String getDirectoryPath(){
        return DATA_DIR;
    }
    public String getFolderPath() {
        return getDirectoryPath() + "/" + getFolderName();
    }

}
