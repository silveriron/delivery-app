package com.delivery.api.domain.user.controller;

import com.delivery.api.domain.user.application.UserConverter;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.api.domain.user.model.CustomUserDetails;
import com.delivery.db.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

  private final UserConverter userConverter;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> me(
      Authentication authentication
  ) {

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

    UserResponse response = userConverter.toResponse(userDetails.getUser());

    return ResponseEntity.ok(response);
  }
}
