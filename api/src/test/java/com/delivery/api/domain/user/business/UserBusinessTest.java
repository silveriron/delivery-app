package com.delivery.api.domain.user.business;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.service.UserService;
import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenException;
import static org.mockito.Mockito.when;

class UserBusinessTest extends MockTestBase {

    LocalDate birthDay = LocalDate.now();
    String email = "test@test.com";
    String password = "test";
    String name = "test";
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private UserBusiness userBusiness;

    UserBusinessTest() {
    }

    @Test
    void 사용자_정보가_주어지면_사용자_정보를_저장한다() {
        // given

        UserRegisterRequest request = UserRegisterRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .birthDay(birthDay)
                .build();
        UserEntity user = new UserEntity(email, name, password, birthDay);

        UserResponse userResponse = UserResponse.builder()
                .email(email)
                .name(name)
                .birthDay(birthDay)
                .role(UserRole.USER.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userService.register(user)).thenReturn(user);
        when(userConverter.toEntity(request)).thenReturn(user);
        when(userConverter.toResponse(user)).thenReturn(userResponse);

        // when
        UserResponse response = userBusiness.register(request);

        // then
        then(response).isEqualTo(userResponse);
        then(response.getEmail()).isEqualTo(email);
        then(response.getName()).isEqualTo(name);
        then(response.getBirthDay()).isEqualTo(birthDay);
        then(response.getRole()).isEqualTo(UserRole.USER.name());
    }

    @Test
    void 이미_존재하는_이메일로_가입하면_에러가_발생한다() {
        // given
        UserRegisterRequest request = UserRegisterRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .birthDay(birthDay)
                .build();
        UserEntity user = new UserEntity(email, name, password, birthDay);
        when(userConverter.toEntity(request)).thenReturn(user);
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        // when & then
        thenException().isThrownBy(() -> userBusiness.register(request));
    }
}