package com.ignite.consumer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ConsumerException.class, Exception.class})
    public ResponseEntity<ConsumerException> consumerExceptionResponseEntity(ConsumerException ex, WebRequest request) {
        return ResponseEntity.status(ex.getCode()).body(new ConsumerException(ex.getCode(),ex.getMessage()));
    }
}
