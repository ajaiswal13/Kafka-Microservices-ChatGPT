package com.ecommerce.orderservice.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
   private String orderId;
   private String productId;
   private Integer quantity;
}
