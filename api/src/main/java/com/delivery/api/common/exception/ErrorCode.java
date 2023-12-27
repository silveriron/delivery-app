package com.delivery.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {
    INVALID_INPUT_VALUE(400, "E001","잘못된 입력값입니다."),
    NOT_FOUND(404, "E002","찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "E005","허용되지 않은 메소드입니다."),
    INTERNAL_SERVER_ERROR(500, "E000","서버 에러입니다.");

    private final int status;
    private final String code;
    private final String message;
}

