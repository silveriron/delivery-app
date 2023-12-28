package com.delivery.api.domain.store.business;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.common.error.StoreErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.store.controller.dto.StoreRegisterRequest;
import com.delivery.api.domain.store.controller.dto.StoreResponse;
import com.delivery.api.domain.store.converter.StoreConverter;
import com.delivery.api.domain.store.service.StoreService;
import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.Mockito.when;

class StoreBusinessTest extends MockTestBase {

    @Mock
    private StoreService storeService;

    @Mock
    private StoreConverter storeConverter;

    @InjectMocks
    private StoreBusiness storeBusiness;

    private StoreEntity chineseStore;
    private StoreEntity japaneseStore;
    private StoreEntity koreanStore;
    private StoreResponse chineseResponse;
    private StoreResponse japaneseResponse;
    private StoreResponse koreanResponse;


    @BeforeEach
    void setup() {
        chineseStore = StoreEntity.builder()
                .name("중국집")
                .description("중국집입니다.")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.CHINESE)
                .build();

        japaneseStore = StoreEntity.builder()
                .name("일식집")
                .description("일식집입니다.")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.JAPANESE)
                .build();

        koreanStore = StoreEntity.builder()
                .name("한식집")
                .description("한식집입니다.")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.KOREAN)
                .build();

        chineseResponse = StoreResponse.builder()
                .id(chineseStore.getId())
                .name(chineseStore.getName())
                .description(chineseStore.getDescription())
                .phone(chineseStore.getPhone())
                .address(chineseStore.getAddress())
                .category(chineseStore.getCategory())
                .status(chineseStore.getStatus())
                .build();

        japaneseResponse = StoreResponse.builder()
                .id(japaneseStore.getId())
                .name(japaneseStore.getName())
                .description(japaneseStore.getDescription())
                .phone(japaneseStore.getPhone())
                .address(japaneseStore.getAddress())
                .category(japaneseStore.getCategory())
                .status(japaneseStore.getStatus())
                .build();

        koreanResponse = StoreResponse.builder()
                .id(koreanStore.getId())
                .name(koreanStore.getName())
                .description(koreanStore.getDescription())
                .phone(koreanStore.getPhone())
                .address(koreanStore.getAddress())
                .category(koreanStore.getCategory())
                .status(koreanStore.getStatus())
                .build();
    }

    @Test
    void 가게_정보를_저장하고_StoreResponse를_반환한다() {
        // given
        StoreRegisterRequest request = StoreRegisterRequest.builder()
                .name("중국집")
                .description("중국집입니다.")
                .phone("010-1234-5678")
                .address("서울시 강남구")
                .category(StoreCategory.CHINESE)
                .build();

        when(storeConverter.toEntity(request)).thenReturn(chineseStore);
        when(storeService.register(chineseStore)).thenReturn(chineseStore);
        when(storeConverter.toResponse(chineseStore)).thenReturn(chineseResponse);

        // when
        StoreResponse storeResponse = storeBusiness.register(request);

        // then
        then(storeResponse.getId()).isEqualTo(chineseStore.getId());
        then(storeResponse.getName()).isEqualTo(chineseStore.getName());
        then(storeResponse.getDescription()).isEqualTo(chineseStore.getDescription());
        then(storeResponse.getPhone()).isEqualTo(chineseStore.getPhone());
        then(storeResponse.getAddress()).isEqualTo(chineseStore.getAddress());
        then(storeResponse.getCategory()).isEqualTo(chineseStore.getCategory());
        then(storeResponse.getStatus()).isEqualTo(chineseStore.getStatus());
    }

    @Test
    void 전체_가게_목록을_StoreResponse_리스트로_반환한다() {
        // given
        when(storeService.getStoresByStatus(StoreStatus.REGISTERED)).thenReturn(List.of(chineseStore, japaneseStore, koreanStore));
        when(storeConverter.toResponse(chineseStore)).thenReturn(chineseResponse);
        when(storeConverter.toResponse(japaneseStore)).thenReturn(japaneseResponse);
        when(storeConverter.toResponse(koreanStore)).thenReturn(koreanResponse);


        // when
        List<StoreResponse> storeResponses = storeBusiness.getRegisteredStores();

        // then
        then(storeResponses).hasSize(3);
        then(storeResponses.get(0).getName()).isEqualTo(chineseStore.getName());
        then(storeResponses.get(1).getName()).isEqualTo(japaneseStore.getName());
        then(storeResponses.get(2).getName()).isEqualTo(koreanStore.getName());
    }

    @Test
    void 카테고리별_가게_목록을_StoreResponse_리스트로_반환한다() {
        // given
        when(storeService.getStoresByCategoryAndStatus(StoreCategory.CHINESE, StoreStatus.REGISTERED)).thenReturn(List.of(chineseStore));
        when(storeConverter.toResponse(chineseStore)).thenReturn(chineseResponse);

        // when
        List<StoreResponse> storeResponses = storeBusiness.getRegisteredStoresByCategory(StoreCategory.CHINESE);

        // then
        then(storeResponses).hasSize(1);
        then(storeResponses.get(0).getName()).isEqualTo(chineseStore.getName());
    }

    @Test
    void 가게이름으로_가게를_조회하고_StoreReponse를_반환한다() {
        // given
        when(storeService.getStoreByNameAndStatus("중국집", StoreStatus.REGISTERED)).thenReturn(Optional.of(chineseStore));
        when(storeConverter.toResponse(chineseStore)).thenReturn(chineseResponse);

        // when
        StoreResponse storeResponse = storeBusiness.getStoreByName("중국집");

        // then
        then(storeResponse.getName()).isEqualTo(chineseStore.getName());
    }

    @Test
    void 가게이름으로_가게를_조회하고_가게가_존재하지_않으면_예외를_발생시킨다() {
        // given
        when(storeService.getStoreByNameAndStatus("중국집", StoreStatus.REGISTERED)).thenReturn(Optional.empty());

        // when
        // then
        thenThrownBy(() -> storeBusiness.getStoreByName("중국집"))
                .isInstanceOf(ApiException.class)
                .hasMessage(StoreErrorCode.NOT_FOUND_STORE.getMessage());
    }

}