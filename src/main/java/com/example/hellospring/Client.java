package com.example.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment prepare = paymentService.prepare(1L, "USD", new BigDecimal("100.42"));
        System.out.println(prepare);

        Payment prepare2 = paymentService.prepare(1L, "USD", new BigDecimal("100.42"));
        System.out.println(prepare2);

        TimeUnit.SECONDS.sleep(3);

        Payment prepare3 = paymentService.prepare(1L, "USD", new BigDecimal("100.42"));
        System.out.println(prepare3);
    }
}
