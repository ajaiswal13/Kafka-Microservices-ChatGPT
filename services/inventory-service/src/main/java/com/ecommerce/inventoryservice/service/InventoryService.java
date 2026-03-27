package com.ecommerce.inventoryservice.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryservice.dto.CreateInventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.entity.InventoryEntity;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public InventoryResponse createInventory(CreateInventoryRequest request){
          if(!inventoryRepository.findByProductId(request.getProductId()).isEmpty()){
            throw new RuntimeException("Inventory already exists for this productId"+ request.getProductId());
          }

          InventoryEntity inventoryEntity = InventoryEntity.builder().
          productId(request.getProductId()).availableQuantity(request.getAvailableQuantity())
          .updatedAt(LocalDateTime.now()).build();

          InventoryEntity savedEntity = inventoryRepository.save(inventoryEntity);
          log.info("Inventory created for product_id" + savedEntity.getProductId());
          return mapToResponse(savedEntity);
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventoryByProductId(String productId){
        InventoryEntity inventoryEntity = inventoryRepository.findByProductId(productId)
        .orElseThrow(() -> new RuntimeException("Product does not exist in the inventory"));
        return mapToResponse(inventoryEntity);
    }

    //This method was even more optimized
    @Transactional(readOnly = true)
    public boolean isInStock(String productId, Integer quantity){
        return inventoryRepository.findByProductId(productId)
         .map(inventory -> inventory.getAvailableQuantity()>= quantity)
         .orElse(false);
    }

    @Transactional
    public void reduceStock(String productId, Integer orderedQuantity){
        InventoryEntity inventoryEntity = inventoryRepository.findByProductId(productId)
        .orElseThrow(() -> new RuntimeException("Product does not exist in the inventory"));
        if(inventoryEntity.getAvailableQuantity()<orderedQuantity){
            log.info("Insufficient stock for this productid" + productId + "for this quantity"+orderedQuantity);
            return;
        }

        inventoryEntity.setAvailableQuantity(inventoryEntity.getAvailableQuantity()-orderedQuantity);
        inventoryEntity.setUpdatedAt(LocalDateTime.now());
        inventoryRepository.save(inventoryEntity);
        log.info("Current stock in inventory for productId" + productId + "after update is "+inventoryEntity.getAvailableQuantity());
    }


    public InventoryResponse mapToResponse(InventoryEntity entity){
        return InventoryResponse.builder().productId(entity.getProductId()).
        availableQuantity(entity.getAvailableQuantity()).build();
    }
}
