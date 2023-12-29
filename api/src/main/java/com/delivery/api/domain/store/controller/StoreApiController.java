package com.delivery.api.domain.store.controller;

import com.delivery.api.domain.store.business.StoreBusiness;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @PostMapping
    public ResponseEntity<StoreResponse> register(
            @Valid
            @RequestBody StoreRegisterRequest request
    ) {

        var store = storeBusiness.register(request);

        return ResponseEntity.ok(store);
    }
}
