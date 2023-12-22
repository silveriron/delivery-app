package com.delivery.api.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private final int code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }


}
