package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class CustomerNotFoundException extends RuntimeException {
    private final long customer;

    public CustomerNotFoundException(long customer, String message) {
        super(message);
        this.customer = customer;
    }

    public CustomerNotFoundException(long customer) {
        this.customer = customer;
    }
}
