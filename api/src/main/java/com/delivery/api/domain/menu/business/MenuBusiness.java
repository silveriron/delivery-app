package com.delivery.api.domain.menu.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.menu.controller.dto.MenuRegisterRequest;
import com.delivery.api.domain.menu.controller.dto.MenuResponse;
import com.delivery.api.domain.menu.converter.MenuConverter;
import com.delivery.api.domain.menu.service.MenuService;
import com.delivery.api.domain.store.service.StoreService;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.store.entity.StoreEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class MenuBusiness {

  private final MenuService menuService;
  private final MenuConverter menuConverter;
  private final StoreService storeService;

  public MenuResponse register(MenuRegisterRequest menuRegisterRequest) {

    StoreEntity storeEntity = storeService.getStoreById(menuRegisterRequest.getStoreId());

    MenuEntity menuEntity = menuConverter.toEntity(menuRegisterRequest, storeEntity);
    MenuEntity savedMenuEntity = menuService.register(menuEntity);
    return menuConverter.toResponse(savedMenuEntity);
  }

  public List<MenuResponse> getMenusByStoreId(Long id) {
      List<MenuEntity> menuEntities = menuService.getMenusByStoreId(id);
      return menuEntities.stream().map(menuConverter::toResponse).toList();
  }
}
