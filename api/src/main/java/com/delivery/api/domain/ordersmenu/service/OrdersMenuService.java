package com.delivery.api.domain.ordersmenu.service;

import com.delivery.db.ordersmenu.entity.OrdersMenuEntity;
import com.delivery.db.ordersmenu.repository.OrdersMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersMenuService {

    private final OrdersMenuRepository ordersMenuRepository;

    public List<OrdersMenuEntity> registerAll(List<OrdersMenuEntity> ordersMenuEntityList) {
        return ordersMenuRepository.saveAll(ordersMenuEntityList);
    }
}
