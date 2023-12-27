package com.delivery.api.domain.user.controller;

import com.delivery.api.domain.user.application.UserBusiness;
import com.delivery.api.domain.user.application.UserConverter;
import com.delivery.api.domain.user.controller.dto.UserLoginRequest;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.db.user.entity.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/users")
public class UserOpenApiController {

    private final UserBusiness userBusiness;
    private final UserConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid
            @RequestBody UserRegisterRequest request
            ) {

        var user = userConverter.toEntity(request);
        var registeredUser = userBusiness.register(user);
        var response = userConverter.toResponse(registeredUser);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid
            @RequestBody UserLoginRequest request
            ) {

        UserEntity user = userBusiness.login(request);

        UserResponse response = userConverter.toResponse(user);

        return ResponseEntity.ok(response);
    }
}
