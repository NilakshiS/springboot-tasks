package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice class to handle exceptions globally
@ControllerAdvice("com.stackroute.Muzix.controller")
public class TrackControllerAdvice extends ResponseEntityExceptionHandler {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    //Exception Handler for TrackNotFoundException
    @ExceptionHandler(value = {TrackNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundConflict(Exception ex, WebRequest request) {
        logger.error("Caught track not found exception");
        String bodyOfResponse = "Track not found!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //Exception Handler for TrackAlreadyExistsException
    @ExceptionHandler(value = {TrackAlreadyExistsException.class})
    protected ResponseEntity<Object> handleAlreadyExistsConflict(Exception ex, WebRequest request) {

        logger.error("Caught track already exists exception");
        String bodyOfResponse = "Track already exists!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}


