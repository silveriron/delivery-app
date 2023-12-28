package com.delivery.db.store.entity;

import com.delivery.db.base.BaseEntity;
import com.delivery.db.store.model.StoreCategory;
import com.delivery.db.store.model.StoreStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "store")
@Getter
@Setter
@NoArgsConstructor
public class StoreEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    private String description;

    @Column(nullable = false, length =50)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;
    @Column(nullable = false, length =50)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @Builder
    public StoreEntity(String name, String address, String phone, String description, StoreCategory category) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.status = StoreStatus.REGISTERED;
        this.category = category;
    }
}
