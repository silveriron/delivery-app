package com.delivery.api.domain.store.service;

import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.model.StoreCategory;
import com.delivery.db.store.model.StoreStatus;
import com.delivery.db.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity register(StoreEntity storeEntity) {
        return storeRepository.save(storeEntity);
    }

    public List<StoreEntity> getStoresByStatus(StoreStatus status) {
        return storeRepository.findAllByStatus(status);
    }

    public List<StoreEntity> getStoresByCategoryAndStatus(StoreCategory storeCategory, StoreStatus storeStatus) {
        return storeRepository.findAllByCategoryAndStatus(storeCategory, storeStatus);
    }
}
