package io.swagger.Persistance;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args){
        EventFileSaver es = new EventFileSaver();

        File f = new File(es.getPath() + "/a.txt");
        try {
            System.out.println(f.createNewFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
