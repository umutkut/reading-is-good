package com.umutku.readingisgood.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class RestResponse<T> {
    private final int statusCode;
    private final HttpStatus status;
    private final T data;

    public RestResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
        this.statusCode = status.value();
    }

}
