package com.umutku.readingisgood.exception;

import lombok.Getter;


@Getter
public class InvalidUserNameException extends RuntimeException {
    private final String userName;

    public InvalidUserNameException(String userName, String message) {
        super(message);
        this.userName = userName;
    }

    public InvalidUserNameException(String userName) {
        this.userName = userName;
    }
}
