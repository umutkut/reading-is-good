package com.umutku.readingisgood.controller.advice;

import com.umutku.readingisgood.exception.NotEnoughStockException;
import com.umutku.readingisgood.exception.OrderNotFoundException;
import com.umutku.readingisgood.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderControllerAdvice {

    public static final String NOT_ENOUGH_STOCK = "Book with id %d does not have enough stock.";
    public static final String ORDER_NOT_FOUND = "Order not found with id %d.";

    @ExceptionHandler({NotEnoughStockException.class})
    public ResponseEntity<RestResponse<String>> notFound(NotEnoughStockException ex) {
        var response = new RestResponse<>(HttpStatus.NOT_ACCEPTABLE, String.format(NOT_ENOUGH_STOCK, ex.getBookId()));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<RestResponse<String>> notFound(OrderNotFoundException ex) {
        var response = new RestResponse<>(HttpStatus.NOT_FOUND, String.format(ORDER_NOT_FOUND, ex.getOrderId()));
        return ResponseEntity.ok(response);
    }
}
