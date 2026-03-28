package com.ecommerce.inventoryservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
     private String message;
     private int status;
     private LocalDateTime timestamp;
}
