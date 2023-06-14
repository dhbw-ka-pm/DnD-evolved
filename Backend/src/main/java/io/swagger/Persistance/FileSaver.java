package io.swagger.Persistance;

import io.swagger.model.XMLModel;

public abstract class FileSaver<T extends XMLModel> {
    private static String BASEPATH  = "XMLDocs/Files";

    public abstract void saveFile(T model);

    public abstract T getFromFile(String filename);

    protected abstract String getFolderName();

    public String getPath(){
        return BASEPATH + "/" + getFolderName();
    }


}
