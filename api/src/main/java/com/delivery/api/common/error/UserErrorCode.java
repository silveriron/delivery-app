package com.delivery.api.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs{

  ALREADY_EXIST_USER(400, "U001", "이미 존재하는 유저입니다."),
  NOT_FOUND_USER(404, "U002", "유저를 찾을 수 없습니다."),
  INVALID_EMAIL_OR_PASSWORD(401, "U003", "이메일 혹은 비밀번호가 잘못되었습니다.");

  private final int status;
  private final String code;
  private final String message;

}
