package com.example.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(10);

        return new Payment(orderId, currency, foreignCurrencyAmount,
                exRate, convertedAmount, validUntil);
    }

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment prepare = paymentService.prepare(1L, "USD", new BigDecimal("100.42"));
        System.out.println(prepare);
    }
}
