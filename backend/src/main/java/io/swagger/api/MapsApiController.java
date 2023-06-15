package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Map;
import io.swagger.persistance.FileSaver;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MapsApiController implements MapsApi {



    private static final Logger log = LoggerFactory.getLogger(MapsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public MapsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<Void> mapsPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Map body) {
        String accept = request.getHeader("Accept");

        body.setSerial(UUID.randomUUID().toString());
        FileSaver<Map> es = new FileSaver<>("maps");
        try {
            es.saveFile(body);

        } catch (JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Map> mapsSerialGet(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("serial") String serial) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<Map>(objectMapper.readValue("{\n  \"sizeX\" : 0,\n  \"serial\" : \"serial\",\n  \"imagePath\" : \"imagePath\",\n  \"name\" : \"name\",\n  \"description\" : \"description\",\n  \"sizeY\" : 6,\n  \"events\" : [ {\n    \"leadsToMapSerial\" : \"leadsToMapSerial\",\n    \"serial\" : \"serial\",\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"location\" : {\n      \"X\" : 0,\n      \"Y\" : 6\n    }\n  }, {\n    \"leadsToMapSerial\" : \"leadsToMapSerial\",\n    \"serial\" : \"serial\",\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"location\" : {\n      \"X\" : 0,\n      \"Y\" : 6\n    }\n  } ]\n}", Map.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Map>(HttpStatus.NOT_IMPLEMENTED);
    }

}