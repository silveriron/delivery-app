package com.delivery.api.controller;

import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.entity.UserRole;
import com.delivery.db.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final UserRepository userRepository;


    @GetMapping("/")
    public ResponseEntity<UserEntity> test() {
        return new ResponseEntity<>(userRepository.save(new UserEntity("test", "test", "test", UserRole.USER, LocalDate.now())), HttpStatus.OK);
    }
}
