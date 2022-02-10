package com.umutku.readingisgood.controller.advice;

import com.umutku.readingisgood.exception.InvalidUserNameException;
import com.umutku.readingisgood.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {


    @ExceptionHandler({InvalidUserNameException.class})
    public ResponseEntity<RestResponse<String>> notFound(InvalidUserNameException ex) {
        var response = new RestResponse<>(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.ok(response);
    }
}
