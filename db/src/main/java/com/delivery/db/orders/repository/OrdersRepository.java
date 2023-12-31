package com.delivery.db.orders.repository;

import com.delivery.db.orders.entity.OrdersEntity;
import com.delivery.db.orders.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

  List<OrdersEntity> findAllByUserId(Long userId);

  List<OrdersEntity> findAllByUserIdAndStatus(Long userId, OrderStatus status);
}
