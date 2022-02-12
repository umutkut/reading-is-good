package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class OrderNotFoundException extends RuntimeException {
    private final long orderId;

    public OrderNotFoundException(long orderId, String message) {
        super(message);
        this.orderId = orderId;
    }

    public OrderNotFoundException(long orderId) {
        this.orderId = orderId;
    }
}
