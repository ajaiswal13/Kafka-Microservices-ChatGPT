package com.ecommerce.inventoryservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.inventoryservice.dto.OrderRequest;
import com.ecommerce.inventoryservice.service.OrderEventService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderEventService orderEventService;
    
    @KafkaListener(topics="orders",groupId = "inventory-group-v3")
    public void consume(OrderRequest orderRequest){
       orderEventService.processOrderEvent(orderRequest);
    }
}
