package com.delivery.api.domain.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthDay;
}
