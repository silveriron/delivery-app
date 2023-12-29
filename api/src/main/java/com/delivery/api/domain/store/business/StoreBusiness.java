package com.delivery.api.domain.store.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.api.domain.store.converter.StoreConverter;
import com.delivery.api.domain.store.service.StoreService;
import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest request) {
        StoreEntity storeEntity = storeConverter.toEntity(request);
        StoreEntity registeredStore = storeService.register(storeEntity);

        return storeConverter.toResponse(registeredStore);
    }

    public List<StoreResponse> getRegisteredStores() {
        List<StoreEntity> stores = storeService.getStoresByStatus(StoreStatus.REGISTERED);

        return stores.stream().map(storeConverter::toResponse).toList();
    }

    public List<StoreResponse> getStoreByName(String name) {
        Optional<StoreEntity> store = storeService.getStoreByNameAndStatus(name, StoreStatus.REGISTERED);

        return store.map(storeEntity -> List.of(storeConverter.toResponse(storeEntity))).orElseGet(List::of);
    }

    public List<StoreResponse> getRegisteredStoresByCategory(StoreCategory storeCategory) {
        List<StoreEntity> stores = storeService.getStoresByCategoryAndStatus(storeCategory, StoreStatus.REGISTERED);

        return stores.stream().map(storeConverter::toResponse).toList();
    }
}
