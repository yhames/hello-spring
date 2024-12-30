package com.example.hellospring.order;

import com.example.hellospring.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {
        var order = orderService.createOrder("0100", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
        assertThat(order.getNo()).isEqualTo("0100");
        assertThat(order.getTotal()).isEqualByComparingTo(BigDecimal.TEN);
    }
}
