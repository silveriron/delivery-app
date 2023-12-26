package com.delivery.db.account.entity;

import com.delivery.db.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "account")
public class AccountEntity extends BaseEntity {

}
