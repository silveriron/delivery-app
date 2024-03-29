package com.delivery.api.domain.user.controller;

import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {


    UserResponse response = userConverter.toResponse(userDetails.getUser());

    return ResponseEntity.ok(response);
  }
}
