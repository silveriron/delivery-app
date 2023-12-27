package com.delivery.api.domain.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String role;
    private LocalDate birthDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
