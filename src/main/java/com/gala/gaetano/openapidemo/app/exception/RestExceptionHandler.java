package com.gala.gaetano.openapidemo.app.exception;

import com.gala.gaetano.openapidemo.api.model.NoDataFoundError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {NoDataException.class})
    public final ResponseEntity<NoDataFoundError> exceptionNotFoundHandler(NoDataException exception){
        LOGGER.info("NotFound exception caught!");

        return new ResponseEntity<>(exception.getError(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
