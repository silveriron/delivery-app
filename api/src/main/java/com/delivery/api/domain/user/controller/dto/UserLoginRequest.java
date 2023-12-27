package com.delivery.api.domain.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
}
