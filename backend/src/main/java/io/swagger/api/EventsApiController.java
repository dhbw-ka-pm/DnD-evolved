package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import io.swagger.model.Event;
import io.swagger.model.Location;
import io.swagger.model.Map;
import io.swagger.model.patchDTOs.PatchEvent;
import io.swagger.persistance.DataHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

// @CrossOrigin(origins = "http://localhost")
// replacs with Angular frontend location for prod
@RestController
public class EventsApiController implements EventsApi {

  private static final Logger log =
      LoggerFactory.getLogger(EventsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  private final DataHandler dataHandler;

  @Autowired
  public EventsApiController(ObjectMapper objectMapper,
                             HttpServletRequest request,
                             DataHandler dataHandler) {
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

  public ResponseEntity<Event>
  eventsGet(@PathVariable(value = "serial") String serial) {
    try {
      return new ResponseEntity<>(dataHandler.getEvent(serial), HttpStatus.OK);
    } catch (DataHandler.SerialNotFoundException e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<String> eventsMapSerialPost(
      @Parameter(in = ParameterIn.PATH, description = "", required = true,
                 schema = @Schema()) @PathVariable("mapSerial")
      String mapSerial,
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true,
                 schema = @Schema()) @Valid @RequestBody Event body) {
    try {
      Map map = dataHandler.getMap(mapSerial);
      String serial = UUID.randomUUID().toString();
      body.setSerial(serial);
      map.addEventsItem(body.getSerial(),
                        new Location(map.getSizeX() / 2, map.getSizeY() / 2));
      dataHandler.putMap(map);
      dataHandler.putEvent(body);
    } catch (JAXBException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (DataHandler.SerialNotFoundException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(body.getSerial(), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> overwriteEvent(
      @Parameter(in = ParameterIn.PATH, description = "", required = true,
                 schema = @Schema()) @PathVariable("serial") String serial,
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true,
                 schema = @Schema()) @Valid @RequestBody PatchEvent body) {
    try {
      Event event = dataHandler.getEvent(serial);
      DataHandler.copyNonNullProperties(body, event);
      dataHandler.updateEvent(serial);
      // TODO Events have to hold their location information. Currently they are
      // not linked
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (DataHandler.SerialNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}
