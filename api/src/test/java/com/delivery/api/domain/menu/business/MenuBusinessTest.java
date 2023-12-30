package com.delivery.api.domain.menu.business;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.domain.menu.controller.dto.MenuRegisterRequest;
import com.delivery.api.domain.menu.controller.dto.MenuResponse;
import com.delivery.api.domain.menu.converter.MenuConverter;
import com.delivery.api.domain.menu.service.MenuService;
import com.delivery.api.domain.store.service.StoreService;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.store.entity.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

class MenuBusinessTest extends MockTestBase {

  @Mock
  private MenuService menuService;

  @Mock
  private MenuConverter menuConverter;

  @Mock
  private StoreService storeService;

  @InjectMocks
  private MenuBusiness menuBusiness;

  private MenuEntity menuEntity;

  private MenuEntity menuEntity2;

  private StoreEntity storeEntity;

  private MenuRegisterRequest menuRegisterRequest;

  private MenuResponse menuResponse;

  private MenuResponse menuResponse2;


  @BeforeEach
  void setup() {

    menuEntity = MenuEntity.builder()
        .name("짜장면")
        .description("짜장면입니다.")
        .price(5000)
        .store(storeEntity)
        .build();

    menuEntity2 = MenuEntity.builder()
        .name("짬뽕")
        .description("짬뽕입니다.")
        .price(6000)
        .store(storeEntity)
        .build();

    storeEntity = StoreEntity.builder()
        .name("중국집")
        .description("중국집입니다.")
        .phone("010-1234-5678")
        .address("서울시 강남구")
        .build();

    menuRegisterRequest = MenuRegisterRequest.builder()
        .name("짜장면")
        .description("짜장면입니다.")
        .price(5000)
        .storeId(storeEntity.getId())
        .build();

    menuResponse = MenuResponse.builder()
        .id(menuEntity.getId())
        .name(menuEntity.getName())
        .description(menuEntity.getDescription())
        .price(menuEntity.getPrice())
        .build();

    menuResponse2 = MenuResponse.builder()
        .id(menuEntity2.getId())
        .name(menuEntity2.getName())
        .description(menuEntity2.getDescription())
        .price(menuEntity2.getPrice())
        .build();
  }

  @Test
  void 메뉴_등록() {
    // given
    when(menuConverter.toEntity(menuRegisterRequest, storeEntity)).thenReturn(menuEntity);
    when(storeService.getStoreById(storeEntity.getId())).thenReturn(storeEntity);
    when(menuService.register(menuEntity)).thenReturn(menuEntity);
    when(menuConverter.toResponse(menuEntity)).thenReturn(menuResponse);

    // when
    var response = menuBusiness.register(menuRegisterRequest);

    // then
    then(response).isNotNull();
    then(response.getId()).isEqualTo(menuEntity.getId());
    then(response.getName()).isEqualTo(menuEntity.getName());
    then(response.getDescription()).isEqualTo(menuEntity.getDescription());
    then(response.getPrice()).isEqualTo(menuEntity.getPrice());
  }

  @Test
  void 가게의_전체_메뉴를_조회한다() {
    // given
    when(menuService.getMenusByStoreId(storeEntity.getId())).thenReturn(List.of(menuEntity, menuEntity2));
    when(menuConverter.toResponse(menuEntity)).thenReturn(menuResponse);
    when(menuConverter.toResponse(menuEntity2)).thenReturn(menuResponse2);

    // when
    var response = menuBusiness.getMenusByStoreId(storeEntity.getId());

    // then
    then(response).isNotNull();
    then(response.size()).isEqualTo(2);
    then(response.get(0).getId()).isEqualTo(menuEntity.getId());
    then(response.get(0).getName()).isEqualTo(menuEntity.getName());
    then(response.get(0).getDescription()).isEqualTo(menuEntity.getDescription());
    then(response.get(0).getPrice()).isEqualTo(menuEntity.getPrice());
    then(response.get(1).getId()).isEqualTo(menuEntity2.getId());
    then(response.get(1).getName()).isEqualTo(menuEntity2.getName());
    then(response.get(1).getDescription()).isEqualTo(menuEntity2.getDescription());
    then(response.get(1).getPrice()).isEqualTo(menuEntity2.getPrice());
  }
}