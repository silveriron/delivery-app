package com.delivery.api.domain.user.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.common.error.UserErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.user.controller.dto.UserLoginRequest;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.model.CustomUserDetails;
import com.delivery.api.domain.user.service.UserService;
import com.delivery.db.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserResponse register(UserRegisterRequest request) {

        UserEntity user = userConverter.toEntity(request);

        userService.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new ApiException(UserErrorCode.ALREADY_EXIST_USER);
                });
        passwordEncoded(user);
        var savedUser = userService.register(user);

        return userConverter.toResponse(savedUser);
    }

    public UserResponse login(UserLoginRequest request) {
        var authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken.unauthenticated(
                request.getEmail(), request.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var user = userDetails.getUser();

        return userConverter.toResponse(user);
    }

    private void passwordEncoded(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
