package com.dhbw.dndEvolved.Persistance;

import java.io.File;
import java.io.Serializable;

public abstract class FileSaver<T extends XMLModel> {
    public static String BASEPATH  = "";

    public abstract void saveFile();

}
