package com.ecommerce.inventoryservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.inventoryservice.dto.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(InventoryNotFoundException.class)
     public ResponseEntity<ApiErrorResponse> handleInventoryNotFound(InventoryNotFoundException ex){
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body(ApiErrorResponse.builder().message(ex.getMessage()).
             status(HttpStatus.NOT_FOUND.value()).timestamp(LocalDateTime.now()).
             build());
     }

     @ExceptionHandler(InventoryAlreadyExistsException.class)
     public ResponseEntity<ApiErrorResponse> handleInventoryAlreadyExists(InventoryAlreadyExistsException ex){
       return ResponseEntity.status(HttpStatus.CONFLICT).
              body(ApiErrorResponse.builder().message(ex.getMessage())
             .status(HttpStatus.CONFLICT.value())
             .timestamp(LocalDateTime.now()).build());
     }

     @ExceptionHandler(InsufficientStockException.class)
     public ResponseEntity<ApiErrorResponse> handleInsufficientStock(InsufficientStockException ex){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).
              body(ApiErrorResponse.builder().message(ex.getMessage())
             .status(HttpStatus.BAD_REQUEST.value())
             .timestamp(LocalDateTime.now()).build());
     }

     @ExceptionHandler(Exception.class)
     public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex){
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
              body(ApiErrorResponse.builder().message("Unexpected internal server error")
             .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
             .timestamp(LocalDateTime.now()).build());
     }
}
