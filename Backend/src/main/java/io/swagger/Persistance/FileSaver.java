package io.swagger.Persistance;

import io.swagger.model.XMLModel;

public abstract class FileSaver<T extends XMLModel> {
    private static String BASEPATH  = "Backend/src/main/resources/XMLDocs/Files";

    public abstract void saveFile(T model);

    public abstract T getFromFile(String filename);

    protected abstract String getFolderName();

    public String getPath(){
        return BASEPATH + "/" + getFolderName();
    }


}
