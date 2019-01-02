package com.itcast.manager.service;

import com.itcast.manager.entity.OrderDetail;
import com.itcast.manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository  orderRepository;

    public void creatOrder(OrderDetail orderDetail) {
        orderRepository.save(orderDetail);
    }
}
