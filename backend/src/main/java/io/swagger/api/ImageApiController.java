package io.swagger.api;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import java.awt.image.BufferedImage;

import io.swagger.persistance.DataHandler;
import io.swagger.persistance.ImageSaver;

// @CrossOrigin(origins = "http://localhost")
@RestController
public class ImageApiController implements ImageApi {
  private static final Logger log =
      LoggerFactory.getLogger(MapsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final DataHandler dataHandler;

  private final ImageSaver imageSaver;

  @Autowired
  public ImageApiController(ObjectMapper objectMapper,
                            HttpServletRequest request, DataHandler dataHandler,
                            ImageSaver imageSaver) {
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
  public ResponseEntity<String> postImageToMap(Resource file,
                                               String mapSerial) {
    try {
      String serial = UUID.randomUUID().toString();
      BufferedImage src = ImageIO.read(file.getInputStream());
      File destination = new File(imageSaver.getFilepath(serial));
      ImageIO.write(src, "jpg", destination);
      dataHandler.getMap(mapSerial).setImagePath(
          "http://localhost:8080/DnDEvolved/v1/images/" + serial);
      dataHandler.getMap(mapSerial).setSizeX(src.getWidth());
      dataHandler.getMap(mapSerial).setSizeY(src.getHeight());
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
