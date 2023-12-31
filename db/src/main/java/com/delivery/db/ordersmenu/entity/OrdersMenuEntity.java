package com.delivery.db.ordersmenu.entity;

import com.delivery.db.base.BaseEntity;
import com.delivery.db.menu.entity.MenuEntity;
import com.delivery.db.orders.entity.OrdersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "orders_menu")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersMenuEntity extends BaseEntity {

  @ManyToOne
  private OrdersEntity orders;

  @ManyToOne
  private MenuEntity menu;

  @Column(nullable = false)
  private Integer quantity;
}
