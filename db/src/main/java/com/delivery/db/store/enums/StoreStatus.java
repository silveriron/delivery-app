package com.delivery.db.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    WAITING("대기");

    private final String description;
}
