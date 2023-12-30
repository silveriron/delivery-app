package com.delivery.db.menu.repository;

import com.delivery.db.menu.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
  List<MenuEntity> findAllByStoreId(Long storeId);
}
