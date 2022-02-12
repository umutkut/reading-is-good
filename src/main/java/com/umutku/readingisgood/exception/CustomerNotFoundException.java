package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class CustomerNotFoundException extends RuntimeException {
    private final long customerId;

    public CustomerNotFoundException(long customerId, String message) {
        super(message);
        this.customerId = customerId;
    }

    public CustomerNotFoundException(long customerId) {
        this.customerId = customerId;
    }
}
