package com.doemaisvida.una.doemaisvida.Controller.exception;

import com.doemaisvida.una.doemaisvida.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound (ResourceNotFoundException e , HttpServletRequest request){
        String error = " Resource Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseIntegrid (DatabaseException e , HttpServletRequest request) {
        String error = " Data Base error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExistsException (UserAlreadyExistsException e , HttpServletRequest request) {
        String error = " User already registered ";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> invalidPasswordException (InvalidPasswordException e , HttpServletRequest request) {
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

    @ExceptionHandler(LoginInvalidException.class)
    public ResponseEntity<StandardError> loginInvalidException (LoginInvalidException e , HttpServletRequest request) {
        String error = " User already registered ";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request) {
        String error = "Erro de validação";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, Object> errors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("rejectedValue", fieldError.getRejectedValue());
            errorDetails.put("message", fieldError.getDefaultMessage());
            errors.put(fieldError.getField(), errorDetails);
        }

        ValidationError validationError = new ValidationError(
                Instant.now(),
                status.value(),
                error,
                "Erro nos campos especificados",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(status).body(validationError);
    }
}