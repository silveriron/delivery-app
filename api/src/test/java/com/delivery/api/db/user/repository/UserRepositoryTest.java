package com.delivery.api.db.user.repository;

import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        UserEntity userEntity1 = new UserEntity("test@test.com", "test", "test", LocalDate.now());
        UserEntity userEntity2 = new UserEntity("test2@test.com", "test2", "test2", LocalDate.now());
        UserEntity userEntity3 = new UserEntity("test3@test.com", "test3", "test3", LocalDate.now());

        userRepository.saveAll(List.of(userEntity1, userEntity2, userEntity3));
    }

    @Test
    void 사용자_이메일로_사용자_정보를_찾는다() {

        Optional<UserEntity> userEntity = userRepository.findByEmail("test@test.com");

        then(userEntity).isPresent();
        then(userEntity.get().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void 존재하지_않는_사용자_이메일로_검색하면_null을_반환한다() {

        Optional<UserEntity> userEntity = userRepository.findByEmail("abc@test.com");

        then(userEntity).isEmpty();
    }

    @Test
    void 사용자_이름으로_사용자_정보를_찾는다() {

        Optional<UserEntity> userEntity = userRepository.findByName("test");

        then(userEntity).isPresent();
        then(userEntity.get().getName()).isEqualTo("test");
    }

    @Test
    void 존재하지_않는_사용자_이름으로_검색하면_null을_반환한다() {

        Optional<UserEntity> userEntity = userRepository.findByName("abc");

        then(userEntity).isEmpty();
    }
}