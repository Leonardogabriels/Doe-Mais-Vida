package com.doemaisvida.una.doemaisvida.resources.exception;

import com.doemaisvida.una.doemaisvida.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound (ResourceNotFoundException e , HttpServletRequest request){
        String error = " Resource Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> DatabaseIntegrid (DatabaseException e , HttpServletRequest request) {
        String error = " Data Base error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> UserAlreadyExistsException (UserAlreadyExistsException e , HttpServletRequest request) {
        String error = " User already registered ";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> InvalidPasswordException (InvalidPasswordException e , HttpServletRequest request) {
        String error = "passwords do not match ";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound (ObjectNotFoundException e , HttpServletRequest request){
        String error = " Object Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
}