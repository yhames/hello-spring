package com.example.hellospring;

import com.example.hellospring.payment.ExRateProvider;
import com.example.hellospring.payment.ExRateProviderStub;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
