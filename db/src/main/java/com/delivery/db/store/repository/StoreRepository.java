package com.delivery.db.store.repository;

import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    Optional<StoreEntity> findByNameAndStatus(String name, StoreStatus status);

    List<StoreEntity> findAllByStatus(StoreStatus status);

    List<StoreEntity> findAllByCategoryAndStatus(StoreCategory category, StoreStatus status);

}
