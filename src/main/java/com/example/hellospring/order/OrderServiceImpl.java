package com.example.hellospring.order;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public List<Order> createOrders(List<OrderReq> reqs) {
        return reqs.stream()
                .map(req -> createOrder(req.no(), req.total()))
                .toList();
    }
}
