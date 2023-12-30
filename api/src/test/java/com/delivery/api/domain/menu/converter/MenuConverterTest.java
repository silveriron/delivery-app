package com.delivery.api.domain.menu.converter;

import com.delivery.api.domain.menu.controller.dto.MenuRegisterRequest;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.store.entity.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MenuConverterTest  {

  private final MenuConverter menuConverter = new MenuConverter();

  private StoreEntity storeEntity;

  @BeforeEach
  void setUp() {
    storeEntity = StoreEntity.builder()
        .name("중국집")
        .description("중국집입니다.")
        .address("서울시 강남구")
        .phone("010-1234-5678")
        .build();
  }


  @Test
  void toEntity() {

    // given
    MenuRegisterRequest request = MenuRegisterRequest.builder()
        .name("짜장면")
        .description("짜장면입니다.")
        .storeId(storeEntity.getId())
        .price(5000)
        .build();

    // when
    var entity = menuConverter.toEntity(request, storeEntity);

    // then
    then(entity).isNotNull();
    then(entity.getName()).isEqualTo(request.getName());
    then(entity.getDescription()).isEqualTo(request.getDescription());
    then(entity.getPrice()).isEqualTo(request.getPrice());
  }

  @Test
  void toResponse() {

    // given
    var entity = MenuEntity.builder()
        .name("짜장면")
        .description("짜장면입니다.")
        .price(5000)
        .build();

    // when
    var response = menuConverter.toResponse(entity);

    // then
    then(response).isNotNull();
    then(response.getName()).isEqualTo(entity.getName());
    then(response.getDescription()).isEqualTo(entity.getDescription());
    then(response.getPrice()).isEqualTo(entity.getPrice());
  }

}