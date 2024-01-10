package com.delivery.api.domain.orders.converter;

import com.delivery.api.common.annotation.Converter;
import com.delivery.api.domain.orders.controller.dto.OrdersRegisterRequest;
import com.delivery.api.domain.orders.controller.dto.OrdersResponse;
import com.delivery.api.domain.ordersmenu.dto.OrderMenuResponse;
import com.delivery.db.orders.entity.OrdersEntity;
import com.delivery.db.orders.enums.OrderStatus;
import com.delivery.db.user.entity.UserEntity;

@Converter
public class OrdersConverter {

    public OrdersEntity toEntity(OrdersRegisterRequest request, UserEntity user) {
        return OrdersEntity.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .totalAmount(request.getTotalAmount())
                .build();
    }

    public OrdersResponse toResponse(OrdersEntity orders) {
        return OrdersResponse.builder()
                .id(orders.getId())
                .status(orders.getStatus())
                .totalAmount(orders.getTotalAmount())
                .orderMenus(orders.getOrdersMenu().stream().map(ordersMenu -> OrderMenuResponse.builder()
                        .id(ordersMenu.getId())
                        .price(ordersMenu.getMenu().getPrice())
                        .quantity(ordersMenu.getQuantity())
                        .name(ordersMenu.getMenu().getName())
                        .build()).toList())
                .build();
    }
}
