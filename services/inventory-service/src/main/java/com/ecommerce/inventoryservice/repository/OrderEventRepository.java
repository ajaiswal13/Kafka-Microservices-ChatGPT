package com.ecommerce.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.inventoryservice.entity.OrderEventEntity;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity,Long>{
    boolean existsByOrderId(String orderId);
}
