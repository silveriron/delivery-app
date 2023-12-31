package com.delivery.api.domain.store.controller.dto;

import com.delivery.db.store.enums.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreRegisterRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @NotNull
    private StoreCategory category;
}
