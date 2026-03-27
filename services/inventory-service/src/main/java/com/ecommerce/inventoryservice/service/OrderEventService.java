package com.ecommerce.inventoryservice.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryservice.dto.OrderRequest;
import com.ecommerce.inventoryservice.entity.OrderEventEntity;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import com.ecommerce.inventoryservice.repository.OrderEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderEventRepository orderEventRepository;
    private final InventoryService inventoryService;

    @Transactional
    public void processOrderEvent(OrderRequest orderRequest){
       log.info("Received order"+ orderRequest);

       OrderEventEntity orderEventEntity = OrderEventEntity.builder()
       .orderId(orderRequest.getOrderId()).productId(orderRequest.getProductId())
       .quantity(orderRequest.getQuantity()).receivedAt(LocalDateTime.now()).build();

       orderEventRepository.save(orderEventEntity);
       log.info("Order event saved in DB with id"+ orderRequest.getOrderId());
       if(inventoryService.isInStock(orderRequest.getProductId(),orderRequest.getQuantity())){
            inventoryService.reduceStock(orderRequest.getProductId(), orderRequest.getQuantity());
            log.info("Updated inventory for this order"+ orderRequest.getOrderId());
       }else{
        log.info("Insufficient stock for this productid" + orderRequest.getProductId() + "for this quantity"+orderRequest.getQuantity()
          +"in this orderid" + orderRequest.getOrderId());
       }
       
     
    }
}
