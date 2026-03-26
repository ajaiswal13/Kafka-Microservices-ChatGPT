package com.ecommerce.inventoryservice.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryservice.dto.OrderRequest;
import com.ecommerce.inventoryservice.entity.OrderEventEntity;
import com.ecommerce.inventoryservice.repository.OrderEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderEventRepository orderEventRepository;

    @Transactional
    public void saveOrderEvent(OrderRequest orderRequest){
       log.info("Received order request in Inventory"+ orderRequest);

       OrderEventEntity orderEventEntity = OrderEventEntity.builder()
       .orderId(orderRequest.getOrderId()).productId(orderRequest.getProductId())
       .quantity(orderRequest.getQuantity()).receivedAt(LocalDateTime.now()).build();

       OrderEventEntity savedEntity = orderEventRepository.save(orderEventEntity);

      log.info("Order saved in DB with id"+ savedEntity.getId());
    }
}
