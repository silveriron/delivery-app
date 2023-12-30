package com.delivery.api.domain.menu.controller;

import com.delivery.api.domain.menu.business.MenuBusiness;
import com.delivery.api.domain.menu.controller.dto.MenuRegisterRequest;
import com.delivery.api.domain.menu.controller.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuApiController {

  private final MenuBusiness menuBusiness;

  @PostMapping
  public MenuResponse register(
      @RequestBody MenuRegisterRequest menuRegisterRequest
  ) {
    return menuBusiness.register(menuRegisterRequest);
  }
}
