package com.example.hellospring;

import com.example.hellospring.data.OrderRepository;
import com.example.hellospring.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("A0001", new BigDecimal("100.42"));
                repository.save(order);
                System.out.println(order);

                Order order2 = new Order("A0001", new BigDecimal("100.42"));
                repository.save(order2);
                System.out.println(order2);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            System.out.println("주문번호 중복 복구 작업");
        }
    }
}
