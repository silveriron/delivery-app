package com.delivery.api.domain.store.service;

import com.delivery.api.base.MockTestBase;
import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import com.delivery.db.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

class StoreServiceTest extends MockTestBase {

    String name = "홍콩반점";
    String description = "중국집";
    String address = "서울시 강남구";
    String phone = "010-1234-5678";
    StoreCategory category = StoreCategory.CHINESE;
    StoreStatus status = StoreStatus.REGISTERED;
    StoreEntity storeEntity = StoreEntity.builder()
            .name(name)
            .description(description)
            .address(address)
            .phone(phone)
            .category(category)
            .build();
    @Mock
    private StoreRepository storeRepository;
    @InjectMocks
    private StoreService storeService;

    @Test
    void 가게정보를_저장한다() {
        // given
        when(storeRepository.save(storeEntity)).thenReturn(storeEntity);

        // when
        var savedStoreEntity = storeService.register(storeEntity);

        // then
        then(savedStoreEntity).isEqualTo(storeEntity);
    }

    @Test
    void 정상등록_상태인_가게_리스트를_조회한다() {
        // given
        when(storeRepository.findAllByStatus(StoreStatus.REGISTERED)).thenReturn(List.of(storeEntity));

        // when
        var storeEntities = storeService.getStoresByStatus(StoreStatus.REGISTERED);

        // then
        then(storeEntities).hasSize(1);
        then(storeEntities.get(0).getName()).isEqualTo(name);
        then(storeEntities.get(0).getDescription()).isEqualTo(description);
        then(storeEntities.get(0).getAddress()).isEqualTo(address);
        then(storeEntities.get(0).getPhone()).isEqualTo(phone);
        then(storeEntities.get(0).getCategory()).isEqualTo(category);
        then(storeEntities.get(0).getStatus()).isEqualTo(status);
    }

    @Test
    void 정상등록_상태인_가게_리스트를_카테고리별로_조회한다() {
        // given
        when(storeRepository.findAllByCategoryAndStatus(StoreCategory.CHINESE, StoreStatus.REGISTERED)).thenReturn(List.of(storeEntity));

        // when
        var storeEntities = storeService.getStoresByCategoryAndStatus(StoreCategory.CHINESE, StoreStatus.REGISTERED);

        // then
        then(storeEntities).hasSize(1);
        then(storeEntities.get(0).getName()).isEqualTo(name);
        then(storeEntities.get(0).getDescription()).isEqualTo(description);
        then(storeEntities.get(0).getAddress()).isEqualTo(address);
        then(storeEntities.get(0).getPhone()).isEqualTo(phone);
        then(storeEntities.get(0).getCategory()).isEqualTo(category);
        then(storeEntities.get(0).getStatus()).isEqualTo(status);
    }

    @Test
    void 가게이름으로_정상등록_상태인_가게를_조회한다() {
        // given
        when(storeRepository.findByNameAndStatus(name, StoreStatus.REGISTERED)).thenReturn(Optional.ofNullable(storeEntity));

        // when
        var storeEntity = storeService.getStoreByNameAndStatus(name, StoreStatus.REGISTERED);

        // then
        then(storeEntity).isPresent();
        then(storeEntity.get().getName()).isEqualTo(name);
        then(storeEntity.get().getDescription()).isEqualTo(description);
        then(storeEntity.get().getAddress()).isEqualTo(address);
        then(storeEntity.get().getPhone()).isEqualTo(phone);
        then(storeEntity.get().getCategory()).isEqualTo(category);
        then(storeEntity.get().getStatus()).isEqualTo(status);
    }

}