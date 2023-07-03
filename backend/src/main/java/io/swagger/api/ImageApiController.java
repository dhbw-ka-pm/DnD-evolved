package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.persistance.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Optional;


@RestController
public class ImageApiController implements ImageApi{
    private static final Logger log = LoggerFactory.getLogger(MapsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final DataHandler dataHandler;

    private final HttpServletResponse response;
    @Autowired
    public ImageApiController(ObjectMapper objectMapper, HttpServletRequest request, HttpServletResponse response, DataHandler dataHandler) {
        this.response = response;
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
        Resource resource =
                new ServletContextResource(request.getServletContext(), dataHandler.getImage(serial).getFilename() );

        return new ResponseEntity<>(dataHandler.getImage(serial), HttpStatus.OK);
    }
}
