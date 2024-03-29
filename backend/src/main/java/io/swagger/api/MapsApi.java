/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.44).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Location;
import io.swagger.model.Map;
import io.swagger.model.Wrapper.EventSerialListWrapper;
import io.swagger.model.Wrapper.MapListWrapper;
import io.swagger.model.patchDTOs.PatchMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

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
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Not OK")
    })
    @RequestMapping(value = "/maps",
            consumes = {"application/xml"},
            method = RequestMethod.POST)
    default ResponseEntity<String> mapsPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Map body) {
        // Populate the response with a success status code
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(summary = "Retrieve a map by serial", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/xml", schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Map not found")})
    @RequestMapping(value = "/maps/{serial}",
            produces = {"application/xml"},
            method = RequestMethod.GET)
    default ResponseEntity<Map> mapsSerialGet(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("serial") String serial) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "delete a Map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Not OK")
    })
    @RequestMapping(value = "/maps/{serial}",
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> mapDelete(@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @PathVariable(value = "serial") String serial) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get a list of all existent Maps")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/xml", array = @ArraySchema(schema = @Schema(implementation = Map.class))))
    })
    @RequestMapping(value = "/maps/",
                            produces = {MediaType.APPLICATION_XML_VALUE},
    method = RequestMethod.GET)

    default ResponseEntity<MapListWrapper> getAllMaps(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "change the location of an Event on a map")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @RequestMapping(value = "/maps/{mapSerial}/events/{eventSerial}/{location_x},{location_y}",
            method = RequestMethod.PATCH,
    consumes = MediaType.APPLICATION_XML_VALUE)
    default ResponseEntity<Void> eventLocationChange(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("eventSerial") String eventSerial,
                                                     @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mapSerial") String mapSerial,
                                                     @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("location_x") int locationX,
                                                     @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("location_y") int locationY
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "delete an event from a map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Not OK")
    })
    @RequestMapping(value = "/maps/{mapSerial}/events/{eventSerial}",
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> mapEventDelete(@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @PathVariable(value = "mapSerial") String mapSerial,
                                                @NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @PathVariable(value = "eventSerial") String eventSerial) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(summary = "change the value of a Map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")
    })
    @RequestMapping(value = "/maps/{serial}",
            method = RequestMethod.PATCH,
    consumes = MediaType.APPLICATION_XML_VALUE)
    default ResponseEntity<Void> patchMap(@PathVariable(value = "serial") String serial, @RequestBody PatchMap body){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "get a list of all Eventserials on a map", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")
    })
    @RequestMapping(value = "/maps/events/{serial}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_XML_VALUE)
    default ResponseEntity<EventSerialListWrapper> getEvents(@PathVariable(value = "serial") String serial){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "get the location for an Event in a map")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")
    })
    @RequestMapping(value = "/maps/{mapSerial}/events/{eventSerial}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_XML_VALUE)
    default ResponseEntity<Location> getEventLocation(@PathVariable(value = "mapSerial") String mapSerial, @PathVariable(value = "eventSerial") String eventSerial){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}


