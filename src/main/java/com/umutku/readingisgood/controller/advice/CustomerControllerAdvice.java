package com.umutku.readingisgood.controller.advice;

import com.umutku.readingisgood.exception.CustomerNotFoundException;
import com.umutku.readingisgood.exception.InvalidUserNameException;
import com.umutku.readingisgood.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {

    public static final String CUSTOMER_NOT_FOUND = "Customer with id %d not found";

    @ExceptionHandler({InvalidUserNameException.class})
    public ResponseEntity<RestResponse<String>> notFound(InvalidUserNameException ex) {
        var response = new RestResponse<>(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<RestResponse<String>> notFound(CustomerNotFoundException ex) {
        var response = new RestResponse<>(HttpStatus.NOT_FOUND, String.format(CUSTOMER_NOT_FOUND,ex.getCustomerId()));
        return ResponseEntity.ok(response);
    }
}
