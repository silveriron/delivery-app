package com.delivery.db.menu.entity;

import com.delivery.db.base.BaseEntity;
import com.delivery.db.store.entity.StoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "menu")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity extends BaseEntity {

  @Column(nullable = false, length = 50)
  private String name;

  private String description;

  @Column(nullable = false)
  private Integer price;

  @ManyToOne
  private StoreEntity store;
}
