package com.ecommerce.orderservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.orderservice.dto.OrderRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String,OrderRequest> kafkaTemplate;

    @Value("${app.kafka.topic.order-created}")
    private String orderTopic;

    public void sendOrderEvent(OrderRequest orderRequest){
        kafkaTemplate.send(orderTopic,orderRequest.getOrderId(),orderRequest);
    }
    
}
