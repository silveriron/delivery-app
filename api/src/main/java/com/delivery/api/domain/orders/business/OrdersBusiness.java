package com.delivery.api.domain.orders.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.menu.service.MenuService;
import com.delivery.api.domain.orders.controller.dto.OrdersRegisterRequest;
import com.delivery.api.domain.orders.controller.dto.OrdersResponse;
import com.delivery.api.domain.orders.converter.OrdersConverter;
import com.delivery.api.domain.orders.service.OrdersService;
import com.delivery.api.domain.ordersmenu.service.OrdersMenuService;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.orders.entity.OrdersEntity;
import com.delivery.db.ordersmenu.entity.OrdersMenuEntity;
import com.delivery.db.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Business
@RequiredArgsConstructor
@Transactional
public class OrdersBusiness {

    private final OrdersService ordersService;
    private final OrdersMenuService ordersMenuService;
    private final MenuService menuService;
    private final OrdersConverter ordersConverter;

    @Transactional(readOnly = true)
    public OrdersResponse getOrdersById(Long orderId) {
        var orderEntity =  ordersService.getOrdersById(orderId);

        return ordersConverter.toResponse(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<OrdersResponse> getOrdersByUserId(Long userId) {
        var orderEntityList = ordersService.getOrdersByUserId(userId);

        return orderEntityList.stream().map(ordersConverter::toResponse).toList();
    }

    public OrdersResponse register(OrdersRegisterRequest ordersRegisterRequest, UserEntity user) {

        OrdersEntity ordersEntity = ordersService.register(ordersConverter.toEntity(ordersRegisterRequest, user));

        List<OrdersMenuEntity> ordersMenuEntityList = ordersRegisterRequest.getOrderItems().stream().map(orderMenu -> {
            MenuEntity menuEntity = menuService.getMenuById(orderMenu.getMenuId());
            return OrdersMenuEntity.builder()
                    .menu(menuEntity)
                    .orders(ordersEntity)
                    .quantity(orderMenu.getQuantity())
                    .build();
        }).toList();

       List<OrdersMenuEntity> savedOrdersMenuList  = ordersMenuService.registerAll(ordersMenuEntityList);

       ordersEntity.setOrdersMenu(savedOrdersMenuList);

       return ordersConverter.toResponse(ordersEntity);
    }
}
