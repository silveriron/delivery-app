package com.delivery.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> exception(ApiException e) {
        return ResponseEntity.status(e.getErrorCode().getCode()).body(e.getErrorCode());
    }
}
