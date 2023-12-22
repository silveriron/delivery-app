package com.delivery.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "잘못된 입력값입니다."),
    NOT_FOUND(404, "찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "허용되지 않은 메소드입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.");

    private final int code;
    private final String message;
}

