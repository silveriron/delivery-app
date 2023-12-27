package com.delivery.api.domain.user.application;

import com.delivery.api.common.annotation.Business;
import com.delivery.db.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;

    public UserEntity register(UserEntity user) {

        return userService.register(user);
    }
}
