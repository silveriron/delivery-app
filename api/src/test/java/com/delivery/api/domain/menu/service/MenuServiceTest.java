package com.delivery.api.domain.menu.service;

import com.delivery.api.base.MockTestBase;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.menu.repository.MenuRepository;
import com.delivery.db.store.entity.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

class MenuServiceTest extends MockTestBase {

  @Mock
  private MenuRepository menuRepository;

  @InjectMocks
  private MenuService menuService;

  private MenuEntity menuEntity;
  private MenuEntity menuEntity2;

  private StoreEntity storeEntity;

  @BeforeEach
  void setup() {

    storeEntity = StoreEntity.builder()
            .name("중국집")
            .description("중국집입니다.")
            .phone("010-1234-5678")
            .address("서울시 강남구")
            .build();



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
  }

  @Test
  void 메뉴_등록() {
    // given
    when(menuRepository.save(menuEntity)).thenReturn(menuEntity);

    // when
    MenuEntity savedMenu = menuService.register(menuEntity);

    // then
    then(savedMenu).isEqualTo(menuEntity);
    then(savedMenu.getName()).isEqualTo(menuEntity.getName());
    then(savedMenu.getDescription()).isEqualTo(menuEntity.getDescription());
    then(savedMenu.getPrice()).isEqualTo(menuEntity.getPrice());
  }

  @Test
  void 가게_아이디로_메뉴_조회() {
    // given
    when(menuRepository.findAllByStoreId(storeEntity.getId())).thenReturn(List.of(menuEntity, menuEntity2));

    // when
    var menus = menuService.getMenusByStoreId(storeEntity.getId());

    // then
    then(menus).hasSize(2);
    then(menus.get(0)).isEqualTo(menuEntity);
    then(menus.get(0).getName()).isEqualTo(menuEntity.getName());
    then(menus.get(0).getDescription()).isEqualTo(menuEntity.getDescription());
    then(menus.get(0).getPrice()).isEqualTo(menuEntity.getPrice());
    then(menus.get(1)).isEqualTo(menuEntity2);
    then(menus.get(1).getName()).isEqualTo(menuEntity2.getName());
    then(menus.get(1).getDescription()).isEqualTo(menuEntity2.getDescription());
    then(menus.get(1).getPrice()).isEqualTo(menuEntity2.getPrice());
  }
}