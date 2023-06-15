package io.swagger.persistance;

import io.swagger.model.XMLModel;

import java.io.File;

public abstract class FileSaver<T extends XMLModel> {

    private static final String DIR = "backend/persistence/xmlDocs/files/";

    public FileSaver() {
        File dir = new File(DIR);
        if (!dir.exists())
            dir.mkdirs();
    }

    public abstract void saveFile(T model);

    public abstract T getFromFile(String filename);

    protected abstract String getFolderName();

    public String getPath() {
        return DIR;
    }


}
