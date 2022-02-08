package com.umutku.readingisgood.controller.advice;

import com.umutku.readingisgood.exception.BookNotFoundException;
import com.umutku.readingisgood.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControllerAdvice {

    public static final String BOOK_NOT_FOUND = "Book with id %d not found";

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<RestResponse<String>> notFound(BookNotFoundException ex) {
        var response = new RestResponse<>(HttpStatus.NOT_FOUND, String.format(BOOK_NOT_FOUND, ex.getBookId()));
        return ResponseEntity.ok(response);
    }
}
