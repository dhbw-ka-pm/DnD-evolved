package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Validated
public interface ImageApi {

    Logger log = LoggerFactory.getLogger(io.swagger.api.MapsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }
    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }
    default Optional<String> getAcceptHeader() {
            return getRequest().map(r -> r.getHeader("Accept"));
        }

        @Operation(description = "get an image by serial")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "409", description = "Not OK")
        })
        @RequestMapping(value = "/images/{serial}",
        method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
        default ResponseEntity<Resource> getImageByPath(@PathVariable(value = "serial")String serial){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
}
