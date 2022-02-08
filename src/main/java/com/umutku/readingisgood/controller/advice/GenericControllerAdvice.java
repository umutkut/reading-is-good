package com.umutku.readingisgood.controller.advice;

import com.umutku.readingisgood.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericControllerAdvice {
    public static final String MISSING_ARGUMENTS_PLEASE_CHECK_THE_REQUEST_BODY = "Missing arguments. Please check the Request Body.";

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse<String>> notFound(MethodArgumentNotValidException ex) {
        var response = new RestResponse<>(HttpStatus.BAD_REQUEST, MISSING_ARGUMENTS_PLEASE_CHECK_THE_REQUEST_BODY);
        return ResponseEntity.ok(response);
    }

}
