package com.example.hellospring;

import com.example.hellospring.data.JdbcOrderRepository;
import com.example.hellospring.data.JpaOrderRepository;
import com.example.hellospring.order.OrderRepository;
import com.example.hellospring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager,
                                     OrderRepository orderRepository) {
        return new OrderService(orderRepository, transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
