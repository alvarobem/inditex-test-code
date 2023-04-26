package com.inditex.prices.price.infraestructure.api.handler;

import com.inditex.prices.shared.exceptions.InditexCustomException;
import com.inditex.prices.shared.response.ErrorRSDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@AllArgsConstructor
public class InditexExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UNHANDLED_EXCEPTION_KEY="exceptions.unhandled";

    private final MessageSource messageSource;

    @ExceptionHandler(InditexCustomException.class)
    public ResponseEntity<Object> handleInditexCustomException(InditexCustomException ex,
                                                               Locale locale){

        if(ex.getReason().getEnableLogging()){
            ex.printStackTrace();
        }

        final var message = messageSource.getMessage(ex.getMessageKey(), new Object[]{} , locale);
        final var errorRSDTO = ErrorRSDTO.builder().message(message).code(ex.getReason().getHttpStatus().value()).build();

        return ResponseEntity.status(ex.getReason().getHttpStatus()).body(errorRSDTO);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInditexCustomException(Exception ex,
                                                               Locale locale){

        ex.printStackTrace();

        final var message = messageSource.getMessage(UNHANDLED_EXCEPTION_KEY, new Object[]{} , locale);
        final var errorRSDTO = ErrorRSDTO.builder().message(message).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRSDTO);
    }
}
