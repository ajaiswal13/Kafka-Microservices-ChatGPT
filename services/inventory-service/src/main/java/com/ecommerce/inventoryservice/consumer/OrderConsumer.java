package com.ecommerce.inventoryservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ecommerce.inventoryservice.dto.OrderRequest;

@Component
public class OrderConsumer {
    
    @KafkaListener(topics="orders",groupId = "inventory-group")
    public void consume(OrderRequest orderRequest){
       System.out.println("Received order request in Inventory"+ orderRequest);
    }
}
