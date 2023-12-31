package com.delivery.db.orders.entity;

import com.delivery.db.base.BaseEntity;
import com.delivery.db.orders.enums.OrderStatus;
import com.delivery.db.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "orders")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity extends BaseEntity {

  private int totalAmount;

  @Column(nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @ManyToOne
  private UserEntity user;
}
