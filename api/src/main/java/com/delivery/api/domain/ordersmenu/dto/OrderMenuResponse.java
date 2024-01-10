package com.delivery.api.domain.ordersmenu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuResponse {
    Long id;
    String name;
    int quantity;
    int price;
}
