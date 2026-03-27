package com.ecommerce.inventoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventoryservice.dto.CreateInventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public InventoryResponse createInventory(@RequestBody CreateInventoryRequest createInventoryRequest){
        return inventoryService.createInventory(createInventoryRequest);
    }

    @GetMapping("/{productId}")
    public InventoryResponse getInventoryByProductId(@PathVariable String productId){
       return inventoryService.getInventoryByProductId(productId);
    }

    @GetMapping("/{productId}/availability")
    public boolean checkAvailability(@PathVariable String productId, @RequestParam Integer quantity){
        return inventoryService.isInStock(productId,quantity);
    }

}
