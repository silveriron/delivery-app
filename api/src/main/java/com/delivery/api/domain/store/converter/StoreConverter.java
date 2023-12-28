package com.delivery.api.domain.store.converter;

import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.db.store.entity.StoreEntity;

public class StoreConverter {
    public static StoreEntity toEntity(StoreRegisterRequest storeRegisterRequest) {
        return StoreEntity.builder()
                .name(storeRegisterRequest.getName())
                .description(storeRegisterRequest.getDescription())
                .phone(storeRegisterRequest.getPhone())
                .address(storeRegisterRequest.getAddress())
                .category(storeRegisterRequest.getCategory())
                .build();
    }

    public static StoreResponse toResponse(StoreEntity storeEntity) {
        return StoreResponse.builder()
                .id(storeEntity.getId())
                .name(storeEntity.getName())
                .description(storeEntity.getDescription())
                .phone(storeEntity.getPhone())
                .address(storeEntity.getAddress())
                .category(storeEntity.getCategory())
                .status(storeEntity.getStatus())
                .build();
    }
}
