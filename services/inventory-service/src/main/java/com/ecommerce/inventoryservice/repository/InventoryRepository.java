package com.ecommerce.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.inventoryservice.entity.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long>{
   Optional<InventoryEntity> findByProductId(String productId);
}
