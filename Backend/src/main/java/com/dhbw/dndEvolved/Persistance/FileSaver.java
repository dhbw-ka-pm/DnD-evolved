package com.dhbw.dndEvolved.Persistance;

import com.dhbw.dndEvolved.model.XMLModel;

import javax.swing.plaf.metal.MetalIconFactory;

public abstract class FileSaver<T extends XMLModel> {
    private static String BASEPATH  = "XMLDocs/Files";

    public abstract void saveFile(T model);

    public abstract T getFromFile(String filename);

    protected abstract String getFolderName();

    private String getPath(){
        return BASEPATH + "/" + getFolderName();
    }


}
