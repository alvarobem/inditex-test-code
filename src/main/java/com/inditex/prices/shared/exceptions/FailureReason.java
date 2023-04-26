package com.inditex.prices.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FailureReason {

    NOT_FOUND(HttpStatus.NOT_FOUND, false),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false);

    private HttpStatus httpStatus;

    private Boolean enableLogging;

    FailureReason(HttpStatus httpStatus, Boolean enableLogging){
        this.httpStatus = httpStatus;
        this.enableLogging = enableLogging;
    }
}
