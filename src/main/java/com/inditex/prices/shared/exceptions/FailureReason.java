package com.inditex.prices.shared.exceptions;

import org.springframework.http.HttpStatus;

public enum FailureReason {

    NOT_FOUND(HttpStatus.NOT_FOUND),
    BAD_REQUEST(HttpStatus.BAD_REQUEST);

    private HttpStatus httpStatus;

    FailureReason(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}
