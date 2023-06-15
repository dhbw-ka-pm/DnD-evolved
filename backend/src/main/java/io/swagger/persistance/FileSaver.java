package io.swagger.persistance;

import io.swagger.model.Map;
import io.swagger.model.XMLModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


public class FileSaver<T extends XMLModel> {

    private final String folderName;

    private static final String XML_ENDING = ".xml";

    private static final String DIR = "backend/persistence/xmlDocs/files/";

    public FileSaver(String folderName) {
        this.folderName = folderName;
        File dir = new File(DIR + "/" + folderName);
        if (!dir.exists())
            dir.mkdirs();
    }

    public void saveFile(T model) throws JAXBException {
        File file = new File(getFolderPath() + model.getSerial() + XML_ENDING);
        JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(model, file);
    };

    public  T getFromFile(String filename){
        return null;
    };

    private String getFolderName(){
        return folderName;
    };


    public static String getDirectoryPath(){
        return DIR;
    }
    public String getFolderPath() {
        return DIR + "/" + getFolderName() + "/";
    }


}
