package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.persistance.DataHandler;
import io.swagger.persistance.ImageSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageApiController implements ImageApi{
    private static final Logger log = LoggerFactory.getLogger(MapsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final DataHandler dataHandler;

    private final ImageSaver imageSaver;

    @Autowired
    public ImageApiController(ObjectMapper objectMapper, HttpServletRequest request, DataHandler dataHandler, ImageSaver imageSaver) {
        this.imageSaver = imageSaver;
        this.objectMapper = objectMapper;
        this.request = request;
        this.dataHandler = dataHandler;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Resource> getImageByPath(String serial) {
        Resource resource = imageSaver.loadAsFile(serial);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> postImageToMap(Resource file, String mapSerial) {
        try {
            String serial = UUID.randomUUID().toString();
            BufferedImage src = ImageIO.read(file.getInputStream());
            File destination = new File(imageSaver.getFilepath(serial));
            ImageIO.write(src, "jpg", destination);
            dataHandler.getMap(mapSerial).setImagePath("http://localhost:8080/DnDEvolved/v1/images/" + serial);
            dataHandler.updateMap(mapSerial);
            return new ResponseEntity<>(serial, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DataHandler.SerialNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
