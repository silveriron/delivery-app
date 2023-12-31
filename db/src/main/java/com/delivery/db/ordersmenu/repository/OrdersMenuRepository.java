package com.delivery.db.ordersmenu.repository;

import com.delivery.db.ordersmenu.entity.OrdersMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersMenuRepository extends JpaRepository<OrdersMenuEntity, Long> {
  List<OrdersMenuEntity> findAllByOrdersId(Long ordersId);
}
