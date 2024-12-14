package com.example.hellospring.payment;

import com.example.hellospring.exrate.ExRateProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(10);

        return new Payment(orderId, currency, foreignCurrencyAmount,
                exRate, convertedAmount, validUntil);
    }
}
