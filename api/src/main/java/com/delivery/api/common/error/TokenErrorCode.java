package com.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeIfs{

    EXPATRIATE_TOKEN(401, "T001", "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "T002", "토큰이 유효하지 않습니다."),
    UNSUPPORTED_TOKEN(401, "T003", "지원하지 않는 토큰입니다.");

    private final int status;
    private final String code;
    private final String message;

}
