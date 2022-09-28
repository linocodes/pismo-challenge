package com.pismo.account.handler;

import com.pismo.lib.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorValidationHandler {

    private final static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS Z");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ElementNotFoundException.class)
    public Map<String, String> genericErrorResponse(ElementNotFoundException ex) {
        return mapErrors(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String, String> handleInternalServerError(Exception ex) {
        return mapErrors(ex);
    }

    private static Map<String, String> mapErrors(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("statusCode", HttpStatus.NOT_FOUND.name());
        errors.put("message", ex.getMessage());
        errors.put("time", dateTime.format(System.currentTimeMillis()));
        return errors;
    }

}