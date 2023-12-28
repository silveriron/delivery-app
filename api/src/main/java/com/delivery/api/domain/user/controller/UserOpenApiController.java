package com.delivery.api.domain.user.controller;

import com.delivery.api.domain.user.business.UserBusiness;
import com.delivery.api.domain.user.controller.dto.UserLoginRequest;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
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

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid
            @RequestBody UserRegisterRequest request
            ) {

        var response = userBusiness.register(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid
            @RequestBody UserLoginRequest request
            ) {

        var response = userBusiness.login(request);

        return ResponseEntity.ok(response);
    }
}
