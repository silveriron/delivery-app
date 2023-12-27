package com.delivery.api.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

        private final ErrorResponse errorResponse;

        public ApiException(ErrorCode errorCode) {
            super(errorCode.getMessage());
            this.errorResponse = new ErrorResponse(errorCode);
        }

        public ApiException(ErrorCode errorCode, String message) {
            super(message);
            this.errorResponse = new ErrorResponse(errorCode, message);
        }

        public ApiException(String message) {
            super(message);
            this.errorResponse = new ErrorResponse(message);
        }

}