package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class NotEnoughStockException extends RuntimeException {
    long bookId;

    public NotEnoughStockException(long bookId, String message) {
        super(message);
        this.bookId = bookId;
    }

    public NotEnoughStockException(long bookId) {
        this.bookId = bookId;
    }
}
