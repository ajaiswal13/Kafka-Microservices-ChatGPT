package com.ecommerce.inventoryservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="order_number", nullable=false)
    private String orderNumber;

    @Column(name="sku_code", nullable=false)
    private String skuCode;

    @Column(nullable=false)
    private Integer quantity;

    @Column(nullable=false,precision=19,scale=2)
    private BigDecimal price;

    @Column(name="received_at", nullable=false)
    private LocalDateTime receivedAt;
}
