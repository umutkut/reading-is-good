package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class BookNotFoundException extends RuntimeException {
    long bookId;

    public BookNotFoundException(long bookId, String message) {
        super(message);
        this.bookId = bookId;
    }

    public BookNotFoundException(long bookId) {
        this.bookId = bookId;
    }
}
