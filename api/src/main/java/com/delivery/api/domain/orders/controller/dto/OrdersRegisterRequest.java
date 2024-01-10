package com.delivery.api.domain.orders.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRegisterRequest {

    List<OrderMenu> orderItems;

    int totalAmount;

    @Getter
    public static class OrderMenu {
        Long menuId;
        int quantity;

    }

}
