package com.delivery.db.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreCategory {
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    SNACK("분식"),
    FASTFOOD("패스트푸드"),
    CHICKEN("치킨"),
    PIZZA("피자"),
    CAFE("카페");

    private final String description;
}
