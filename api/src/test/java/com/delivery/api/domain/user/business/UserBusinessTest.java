package com.delivery.api.domain.user.business;

import com.delivery.api.base.MockTestBase;
import com.delivery.api.domain.token.service.TokenServiceIfs;
import com.delivery.api.domain.user.controller.dto.UserLoginRequest;
import com.delivery.api.domain.user.controller.dto.UserRegisterRequest;
import com.delivery.api.domain.user.controller.dto.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.model.CustomUserDetails;
import com.delivery.api.domain.user.service.UserService;
import com.delivery.db.user.entity.UserEntity;
import com.delivery.db.user.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenException;
import static org.mockito.Mockito.when;

class UserBusinessTest extends MockTestBase {


    @Mock
    private UserService userService;
    LocalDate birthDay = LocalDate.now();
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private UserBusiness userBusiness;
    String email = "test@test.com";
    String password = "test";
    String name = "test";
    @Mock
    private TokenServiceIfs tokenService;
    @Mock
    private AuthenticationManager authenticationManager;
    private UserRegisterRequest request;
    private UserEntity user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {



        request = UserRegisterRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .birthDay(birthDay)
                .build();
        user = new UserEntity(email, name, password, birthDay);

        userResponse = UserResponse.builder()
                .email(email)
                .name(name)
                .birthDay(birthDay)
                .role(UserRole.USER.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
    }

    @Test
    void 사용자_정보가_주어지면_사용자_정보를_저장한다() {
        // given
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userService.register(user)).thenReturn(user);
        when(tokenService.generateAccessToken(user.getId())).thenReturn("accessToken");
        when(tokenService.generateRefreshToken(user.getId())).thenReturn("refreshToken");
        when(userConverter.toEntity(request)).thenReturn(user);
        when(userConverter.toResponse(user, "accessToken", "refreshToken")).thenReturn(userResponse);

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

    @Test
    void 로그인이_완료되면_사용자_응답으로_변환한다() {
        // given
        UserLoginRequest request = UserLoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        CustomUserDetails userDetails = new CustomUserDetails(user);

        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        )).thenReturn(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        when(tokenService.generateAccessToken(user.getId())).thenReturn("accessToken");
        when(tokenService.generateRefreshToken(user.getId())).thenReturn("refreshToken");
        when(userConverter.toResponse(user, "accessToken", "refreshToken")).thenReturn(userResponse);

        // when
        var loginResponse = userBusiness.login(request);

        //then
        then(loginResponse).isEqualTo(userResponse);
        then(loginResponse.getEmail()).isEqualTo(email);
        then(loginResponse.getName()).isEqualTo(name);
        then(loginResponse.getBirthDay()).isEqualTo(birthDay);
        then(loginResponse.getRole()).isEqualTo(UserRole.USER.name());
        then(loginResponse.getAccessToken()).isEqualTo("accessToken");
        then(loginResponse.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void 존재하지_않는_사용자_정보로_로그인하면_에러가_발생한다() {
        // given
        UserLoginRequest request = UserLoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        )).thenThrow(new BadCredentialsException("BadCredentialsException"));

        // when & then
        thenException().isThrownBy(() -> userBusiness.login(request));
    }
}