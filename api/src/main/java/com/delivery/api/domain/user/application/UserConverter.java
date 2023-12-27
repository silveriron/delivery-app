package com.delivery.api.domain.user.application;

import com.delivery.api.common.annotation.Converter;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.db.user.entity.UserEntity;

@Converter
public class UserConverter {
    public UserEntity toEntity(UserRegisterRequest request) {
        return new UserEntity(request.getEmail(), request.getName(), request.getPassword(), request.getBirthDay());
    }

    public UserResponse toResponse(UserEntity registeredUser) {
        return UserResponse.builder()
                .email(registeredUser.getEmail())
                .name(registeredUser.getName())
                .role(registeredUser.getRole().name())
                .id(registeredUser.getId())
                .birthDay(registeredUser.getBirthDay())
                .createdAt(registeredUser.getCreatedAt())
                .updatedAt(registeredUser.getUpdatedAt())
                .build();
    }
}
