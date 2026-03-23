package com.ecommerce.orderservice.service;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.producer.OrderProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;

    public String createOrder(OrderRequest orderRequest){
        orderProducer.sendOrderEvent(orderRequest);
        return "Order created with id" + orderRequest.getOrderId();
    }
}
