package com.delivery.db.account.entity;

import com.delivery.db.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "account")
public class AccountEntity extends BaseEntity {

}
