/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.44).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Event;
import io.swagger.model.Location;
import io.swagger.model.Map;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@javax.annotation.processing.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-11T16:16:55.027943953Z[GMT]")
@Validated
public interface MapsApi {

    Logger log = LoggerFactory.getLogger(MapsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @Operation(summary = "Add a new map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    @RequestMapping(value = "/maps",
            consumes = {"application/xml"},
            method = RequestMethod.POST)
    default ResponseEntity<Void> mapsPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Map body) {
        // Populate the response with a success status code
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a map by serial", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/xml", schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Map not found")})
    @RequestMapping(value = "/maps/{serial}",
            produces = {"application/xml"},
            method = RequestMethod.GET)
    default ResponseEntity<Map> mapsSerialGet(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("serial") String serial) {
        // Create a sample Map object with example values
        Map sampleMap = new Map();
        sampleMap.setSerial("sampleSerial");
        sampleMap.setName("Sample Map");
        sampleMap.setDescription("This is a sample map");
        sampleMap.setImagePath("sampleImagePath");
        sampleMap.setSizeX(10);
        sampleMap.setSizeY(8);

        // Create a sample Event and add it to the map
        Event sampleEvent = new Event();
        sampleEvent.setSerial("eventSerial");
        sampleEvent.setName("Sample Event");
        sampleEvent.setDescription("This is a sample event");
        sampleEvent.setLocation(new Location().X(5).Y(5));

        sampleMap.setEvents(Collections.singletonList(sampleEvent));

        // Return the sample map in the response
        return new ResponseEntity<>(sampleMap, HttpStatus.OK);
    }

}


