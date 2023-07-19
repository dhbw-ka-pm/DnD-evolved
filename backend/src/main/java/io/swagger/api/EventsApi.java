/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.44).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Event;
import io.swagger.model.patchDTOs.PatchEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

@Validated
public interface EventsApi {

    Logger log = LoggerFactory.getLogger(EventsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }


    @Operation(summary = "Retrieve events by serial", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/xml", schema = @Schema(implementation = Event.class)))})
    @RequestMapping(value = "/events/{serial}",
            produces = {"application/xml"},
            method = RequestMethod.GET)
    default ResponseEntity<Event> eventsGet(@PathVariable(value = "serial") String serial) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/xml")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {\n  \"serial\" : \"serial\",\n  \"serial\" : \"serial\",\n  \"name\" : \"name\",\n  \"description\" : \"description\"}, {\n  \"leadsToMapSerial\" : \"leadsToMapSerial\",\n  \"serial\" : \"serial\",\n  \"name\" : \"name\",\n  \"description\" : \"description\": {\n    \"X\" : 0,\n    \"Y\" : 6\n  }\n} ]", Event.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/xml", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(summary = "Add a new event to a map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Map not found")})
    @RequestMapping(value = "/events/{mapSerial}",
            consumes = {"application/xml"},
            method = RequestMethod.POST)
    default ResponseEntity<String> eventsMapSerialPost(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mapSerial") String mapSerial, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Event body) throws JAXBException {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(summary = "modify a specific event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @RequestMapping(value = "/events/edit/{serial}",
            consumes = {"application/xml"},
            method = RequestMethod.PATCH)
    default ResponseEntity<Void> overwriteEvent(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("serial") String serial,
                                                @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody PatchEvent body
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

