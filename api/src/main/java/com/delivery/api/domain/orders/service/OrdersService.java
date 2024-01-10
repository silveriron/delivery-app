package com.delivery.api.domain.orders.service;

import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.db.orders.entity.OrdersEntity;
import com.delivery.db.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersEntity getOrdersById(Long orderId) {

        return ordersRepository.findById(orderId).orElseThrow(() -> new ApiException(ErrorCode.INVALID_INPUT_VALUE));
    }


    public List<OrdersEntity> getOrdersByUserId(Long userId) {

        return ordersRepository.findAllByUserId(userId);
    }

    public OrdersEntity register(OrdersEntity orderEntity) {
        return ordersRepository.save(orderEntity);
    }
}
