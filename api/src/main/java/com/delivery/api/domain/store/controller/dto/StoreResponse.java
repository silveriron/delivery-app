package com.delivery.api.domain.store.controller.dto;

import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private String phone;
    private String address;
    private StoreStatus status;
    private StoreCategory category;
}
