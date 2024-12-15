package com.example.hellospring;

import com.example.hellospring.data.OrderRepository;
import com.example.hellospring.order.Order;
import com.example.hellospring.payment.PaymentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        Order order = new Order("A0001", new BigDecimal("100.42"));
        repository.save(order);
        System.out.println(order);
    }
}
