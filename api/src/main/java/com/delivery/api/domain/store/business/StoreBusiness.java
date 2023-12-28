package com.delivery.api.domain.store.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.api.domain.store.converter.StoreConverter;
import com.delivery.api.domain.store.service.StoreService;
import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.model.StoreStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
}
