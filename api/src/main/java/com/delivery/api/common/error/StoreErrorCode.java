package com.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements ErrorCodeIfs {

    ALREADY_EXIST_STORE(400, "S001", "이미 존재하는 가게입니다."),
    NOT_FOUND_STORE(404, "S002", "가게를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
