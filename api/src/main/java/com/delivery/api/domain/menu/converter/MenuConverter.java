package com.delivery.api.domain.menu.converter;

import com.delivery.api.common.annotation.Converter;
import com.delivery.api.domain.menu.controller.dto.MenuRegisterRequest;
import com.delivery.api.domain.menu.controller.dto.MenuResponse;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.store.entity.StoreEntity;

@Converter
public class MenuConverter {

  public MenuEntity toEntity(MenuRegisterRequest request, StoreEntity storeEntity) {
    return MenuEntity.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .store(storeEntity)
        .build();
  }

  public MenuResponse toResponse(MenuEntity entity) {
    return MenuResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .price(entity.getPrice())
        .build();
  }
}
