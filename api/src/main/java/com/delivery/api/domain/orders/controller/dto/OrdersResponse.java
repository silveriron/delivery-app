package com.delivery.api.domain.orders.controller.dto;

import com.delivery.api.domain.ordersmenu.dto.OrderMenuResponse;
import com.delivery.db.orders.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {
    Long id;
    OrderStatus status;
    int totalAmount;
    List<OrderMenuResponse> orderMenus;
}
