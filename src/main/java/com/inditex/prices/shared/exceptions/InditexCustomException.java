package com.inditex.prices.shared.exceptions;

import lombok.Getter;

@Getter
public class InditexCustomException extends RuntimeException{

    private final String messageKey;

    private final FailureReason reason;
    public InditexCustomException(String messageKey, FailureReason reason){
        super(messageKey);
        this.messageKey = messageKey;
        this.reason = reason;
    }

}
