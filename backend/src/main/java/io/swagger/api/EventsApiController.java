package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Event;
import io.swagger.model.Map;
import io.swagger.persistance.DataHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final DataHandler dataHandler;

    @Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request, DataHandler dataHandler) {
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

    public ResponseEntity<Event> eventsGet(@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "serial", required = true) String serial) {
        String accept = request.getHeader("Accept");
            if (accept != null && accept.contains("application/xml")) {
                try {
                    return new ResponseEntity<>(dataHandler.getEvent(serial), HttpStatus.OK);
                } catch (DataHandler.SerialNotFoundException e) {
                    log.error(e.getMessage(), e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> eventsMapSerialPost(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mapSerial") String mapSerial, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Event body){
        String accept = request.getHeader("Accept");


        try {
            Map map = dataHandler.getMap(mapSerial);
            if(body.getSerial() == null) {
                String serial = UUID.randomUUID().toString();
                body.setSerial(serial);
                map.addEvent(serial);
            }
            dataHandler.putMap(map);
            dataHandler.putEvent(body);
        }
        catch (JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (DataHandler.SerialNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(body.getSerial(), HttpStatus.OK);
    }

}
