package com.delivery.api.domain.user.application;

import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void 사용자_정보가_주어지면_사용자_정보를_저장한다() {
    // given
    LocalDate birthDay = LocalDate.now();
    UserEntity user = new UserEntity("test@test.com", "test", "test", birthDay);
    when(userRepository.save(user)).thenReturn(user);

    // when
    UserEntity savedUser = userService.register(user);

    // then
    then(savedUser).isEqualTo(user);
    then(savedUser.getEmail()).isEqualTo("test@test.com");
    then(savedUser.getName()).isEqualTo("test");
    then(savedUser.getPassword()).isEqualTo("test");
    then(savedUser.getBirthDay()).isEqualTo(birthDay);
  }

  @Test
  void 이메일이_주어지면_사용자_정보를_찾는다() {
    // given
    String email = "test@test.com";
    UserEntity user = new UserEntity(email, "test", "test", LocalDate.now());
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

    // when
    Optional<UserEntity> foundUser = userService.findByEmail(email);

    // then
    then(foundUser).isNotEmpty();
    then(foundUser.get()).isEqualTo(user);
  }
}