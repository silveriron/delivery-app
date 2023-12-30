package com.delivery.api.domain.menu.service;

import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuRepository menuRepository;

  public MenuEntity register(MenuEntity menuEntity) {
    return menuRepository.save(menuEntity);
  }

  public List<MenuEntity> getMenusByStoreId(Long storeId) {
    return menuRepository.findAllByStoreId(storeId);
  }
}


