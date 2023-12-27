package com.delivery.api.domain.user.application;

import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity user) {

        return userRepository.save(user);
    }
}
