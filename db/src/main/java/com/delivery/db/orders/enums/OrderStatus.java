package com.delivery.db.orders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

  PENDING("PENDING"),
  COOKING("COOKING"),
  DELIVERING("DELIVERING"),
  DELIVERED("DELIVERED"),
  CANCELED("CANCELED");

  private final String status;
}
