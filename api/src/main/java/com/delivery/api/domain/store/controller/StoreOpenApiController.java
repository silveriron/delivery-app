package com.delivery.api.domain.store.controller;

import com.delivery.api.domain.store.business.StoreBusiness;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getStores() {

        var stores = storeBusiness.getRegisteredStores();

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/category")
    public ResponseEntity<List<StoreResponse>> getStoresByCategory(@RequestParam("category") StoreCategory category) {

        var stores = storeBusiness.getRegisteredStoresByCategory(category);

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StoreResponse>> getStoresByKeyword(@RequestParam("keyword") String keyword) {

        var stores = storeBusiness.getStoreByName(keyword);

        return ResponseEntity.ok(stores);
    }

}
