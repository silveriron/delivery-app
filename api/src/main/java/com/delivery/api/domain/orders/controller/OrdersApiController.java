package com.delivery.api.domain.orders.controller;

import com.delivery.api.domain.orders.business.OrdersBusiness;
import com.delivery.api.domain.orders.controller.dto.OrdersRegisterRequest;
import com.delivery.api.domain.orders.controller.dto.OrdersResponse;
import com.delivery.api.domain.user.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersApiController {

    private final OrdersBusiness ordersBusiness;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersResponse> getOrdersById(

            @PathVariable(name = "orderId") String orderId
    ) {

        var orders = ordersBusiness.getOrdersById(Long.valueOf(orderId));

        return ResponseEntity.ok(orders);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrdersResponse>> getOrdersByUserId(
            @PathVariable(name = "userId") String userId
    ) {

       var ordersList = ordersBusiness.getOrdersByUserId(Long.valueOf(userId));

         return ResponseEntity.ok(ordersList);
    }

    @PostMapping
    public ResponseEntity<OrdersResponse> register(
            @RequestBody OrdersRegisterRequest ordersRegisterRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {

       var orders = ordersBusiness.register(ordersRegisterRequest, userDetails.getUser());

       return ResponseEntity.ok(orders);
    }

}
