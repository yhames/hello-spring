package com.example.hellospring;

import com.example.hellospring.payment.ExRateProvider;
import com.example.hellospring.payment.ExRateProviderStub;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

public class TestObjectFactory {

    @Bean
    public PaymentService paymentService(ExRateProvider exRateProvider) {
        return new PaymentService(exRateProvider);
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }
}
