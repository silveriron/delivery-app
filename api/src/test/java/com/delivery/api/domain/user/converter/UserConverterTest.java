package com.delivery.api.domain.user.converter;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.db.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.assertj.core.api.BDDAssertions.then;

class UserConverterTest extends MockTestBase {

  @InjectMocks
  private UserConverter userConverter;

  @Test
  void 사용자_가입_요청을_사용자_엔티티로_변환한다() {
    // given
    UserRegisterRequest request = UserRegisterRequest.builder()
        .email("test@test.com")
        .password("test")
        .name("test")
        .birthDay(LocalDate.now())
        .build();

    // when
    var user = userConverter.toEntity(request);

    // then
    then(user.getEmail()).isEqualTo(request.getEmail());
    then(user.getPassword()).isEqualTo(request.getPassword());
    then(user.getName()).isEqualTo(request.getName());
    then(user.getBirthDay()).isEqualTo(request.getBirthDay());
  }

  @Test
  void 사용자_엔티티를_사용자_응답으로_변환한다() {
    // given
    UserEntity user = new UserEntity("test@test.com", "test", "test", LocalDate.now());

    // when
    var response = userConverter.toResponse(user);

    // then
    then(response.getEmail()).isEqualTo(user.getEmail());
    then(response.getName()).isEqualTo(user.getName());
    then(response.getRole()).isEqualTo(user.getRole().name());
    then(response.getId()).isEqualTo(user.getId());
    then(response.getBirthDay()).isEqualTo(user.getBirthDay());
    then(response.getCreatedAt()).isEqualTo(user.getCreatedAt());
    then(response.getUpdatedAt()).isEqualTo(user.getUpdatedAt());
  }
}