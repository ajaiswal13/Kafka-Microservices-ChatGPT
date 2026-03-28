package com.ecommerce.inventoryservice.exception;

public class InventoryAlreadyExistsException extends RuntimeException{
      public InventoryAlreadyExistsException(String message){
          super(message);
      }
}
