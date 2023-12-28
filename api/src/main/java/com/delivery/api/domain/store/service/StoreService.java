package com.delivery.api.domain.store.service;

import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import com.delivery.db.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<StoreEntity> getStoreByNameAndStatus(String name, StoreStatus storeStatus) {
        return storeRepository.findByNameAndStatus(name, storeStatus);
    }
}
