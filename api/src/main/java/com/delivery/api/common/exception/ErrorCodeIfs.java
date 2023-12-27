package com.delivery.api.common.exception;

public interface ErrorCodeIfs {

  String getCode();
  String getMessage();
  int getStatus();
}
