package com.delivery.api.domain.store.converter;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.BDDAssertions.then;

class StoreConverterTest extends MockTestBase {

    @InjectMocks
    private StoreConverter storeConverter;

    private StoreEntity storeEntity;



    @Test
    void StoreRegisterRequest를_StoreEntity로_변환한다() {
        // given
        StoreRegisterRequest storeRegisterRequest = StoreRegisterRequest.builder()
                .name("홍콩반점")
                .description("중식집")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.CHINESE)
                .build();
        // when
        StoreEntity storeEntity = storeConverter.toEntity(storeRegisterRequest);
        // then

        then(storeRegisterRequest.getName()).isEqualTo(storeEntity.getName());
        then(storeRegisterRequest.getDescription()).isEqualTo(storeEntity.getDescription());
        then(storeRegisterRequest.getPhone()).isEqualTo(storeEntity.getPhone());
        then(storeRegisterRequest.getAddress()).isEqualTo(storeEntity.getAddress());
        then(storeRegisterRequest.getCategory()).isEqualTo(storeEntity.getCategory());
    }

    @Test
    void StoreEntity를_StoreResponse로_변환한다() {
        // given
        StoreEntity storeEntity = StoreEntity.builder()
                .name("홍콩반점")
                .description("중식집")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.CHINESE)
                .build();
        // when
        StoreResponse storeResponse = storeConverter.toResponse(storeEntity);
        // then

        then(storeResponse.getId()).isEqualTo(storeEntity.getId());
        then(storeResponse.getName()).isEqualTo(storeEntity.getName());
        then(storeResponse.getDescription()).isEqualTo(storeEntity.getDescription());
        then(storeResponse.getPhone()).isEqualTo(storeEntity.getPhone());
        then(storeResponse.getAddress()).isEqualTo(storeEntity.getAddress());
        then(storeResponse.getCategory()).isEqualTo(storeEntity.getCategory());
    }

}