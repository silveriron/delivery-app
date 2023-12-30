package com.delivery.api.domain.menu.controller;

import com.delivery.api.domain.menu.business.MenuBusiness;
import com.delivery.api.domain.menu.controller.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open-api/menus")
@RequiredArgsConstructor
public class MenuOpenApiController {

  private final MenuBusiness menuBusiness;

  @GetMapping("/stores/{id}")
  public List<MenuResponse> getMenusByStoreId(
      @PathVariable Long id) {
    return menuBusiness.getMenusByStoreId(id);
  }
}
