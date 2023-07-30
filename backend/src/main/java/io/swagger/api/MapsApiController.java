package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.model.Location;
import io.swagger.model.Map;
import io.swagger.model.Wrapper.EventSerialListWrapper;
import io.swagger.model.Wrapper.MapListWrapper;
import io.swagger.model.patchDTOs.PatchMap;
import io.swagger.persistance.DataHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

// @CrossOrigin(origins = "http://localhost") // replacs with Angular frontend location for prod
@RestController
public class MapsApiController implements MapsApi {


    private static final Logger log = LoggerFactory.getLogger(MapsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final DataHandler dataHandler;

    @Autowired
    public MapsApiController(ObjectMapper objectMapper, HttpServletRequest request, DataHandler dataHandler) {
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
    public ResponseEntity<String> mapsPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Map body) {
        body.setSerial(UUID.randomUUID().toString());
        try {
            body.setEvents(new HashMap<>());
            dataHandler.putMap(body);

        } catch (JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(body.getSerial(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map> mapsSerialGet(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("serial") String serial) {
            try {
                return new ResponseEntity<>(dataHandler.getMap(serial), HttpStatus.OK);
            } catch (DataHandler.SerialNotFoundException e) {
                log.error(e.getMessage(), e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @Override
    public ResponseEntity<Void> mapDelete(@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @PathVariable(value = "serial", required = true) String serial) {
        try {
            dataHandler.removeMap(serial);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataHandler.SerialNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<MapListWrapper> getAllMaps(){
        try {
            List<Map> maps = dataHandler.getAllMaps();
            MapListWrapper mapListWrapper = new MapListWrapper();
            mapListWrapper.setMaps(maps);
            return new ResponseEntity<>(mapListWrapper, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving all maps", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<Void> eventLocationChange(String eventSerial, String mapSerial, int locationX, int locationY) {

        try {
            Map map = dataHandler.getMap(mapSerial);
            Location loc = map.getEvents().get(eventSerial);
            loc.setX(locationX);
            loc.setY(locationY);
            dataHandler.updateMap(mapSerial);
        } catch (DataHandler.SerialNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JAXBException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Void> mapEventDelete(String mapSerial, String eventSerial) {
        try {
            dataHandler.removeEvent(eventSerial);
            dataHandler.getMap(mapSerial).getEvents().remove(eventSerial);
            dataHandler.updateMap(mapSerial);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataHandler.SerialNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JAXBException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchMap(String serial, PatchMap body) {
        try {
            Map existingMap = dataHandler.getMap(serial);
            DataHandler.copyNonNullProperties(body, existingMap);
            dataHandler.updateMap(serial);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataHandler.SerialNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<EventSerialListWrapper> getEvents(String serial) {
        try {
            Map map = dataHandler.getMap(serial);
            EventSerialListWrapper esw = new EventSerialListWrapper();
            esw.setEvents(map.getEvents().keySet());
            return new ResponseEntity<>(esw, HttpStatus.OK);
        } catch (DataHandler.SerialNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Location> getEventLocation(String mapSerial, String eventSerial) {
        try {
            Map map = dataHandler.getMap(mapSerial);
            Location l = map.getEvents().get(eventSerial);
            if(l == null){
                throw new DataHandler.SerialNotFoundException("the serial of the Event is not on this map");
            }
            return new ResponseEntity<>(l, HttpStatus.OK);
        } catch (DataHandler.SerialNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
