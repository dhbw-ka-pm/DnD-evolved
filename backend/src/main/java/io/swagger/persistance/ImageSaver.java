package io.swagger.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageSaver {
    static final String DATA_DIR = "backend/persistence/xmlDocs/files/images";





    private final static String JPG_ENDING = ".jpg";

    @Autowired
    public ImageSaver() {
        File dir = new File(DATA_DIR);
        if (!dir.exists())
            dir.mkdirs();
    }

    public void SaveFile(File file, String serial){

    }

    public String getSaveLocation(){
        return DATA_DIR;
    }


    public String storeFile(MultipartFile file) {
        return null;
    }


    public Resource loadAsFile(String serial){
            Resource resource = new PathResource(DATA_DIR + "/" + serial + JPG_ENDING);
                return resource;
    }
    }

