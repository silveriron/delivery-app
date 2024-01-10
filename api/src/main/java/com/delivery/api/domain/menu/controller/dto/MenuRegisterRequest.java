package com.delivery.api.domain.menu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRegisterRequest {

  private String name;
  private String description;
  private Integer price;
  private Long storeId;
}
