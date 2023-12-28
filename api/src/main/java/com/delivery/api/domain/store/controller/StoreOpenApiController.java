package com.delivery.api.domain.store.controller;

import com.delivery.api.domain.store.business.StoreBusiness;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @PostMapping
    public ResponseEntity<StoreResponse> register(
            @Valid
            @RequestBody StoreRegisterRequest request
    ) {

        var store = storeBusiness.register(request);

        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getStores() {

        var stores = storeBusiness.getRegisteredStores();

        return ResponseEntity.ok(stores);
    }
}
