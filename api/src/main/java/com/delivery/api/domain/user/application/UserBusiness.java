package com.delivery.api.domain.user.application;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.common.exception.ErrorCode;
import com.delivery.api.domain.user.controller.dto.UserLoginRequest;
import com.delivery.api.domain.user.domain.CustomUserDetails;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserEntity register(UserEntity user) {

        userService.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new ApiException(ErrorCode.ALREADY_EXIST_USER);
                });

        passwordEncoded(user);

        return userService.register(user);
    }

    public UserEntity login(UserLoginRequest request) {
        var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }

    private void passwordEncoded(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
