package com.example.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public abstract class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(10);

        return new Payment(orderId, currency, foreignCurrencyAmount,
                exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;

    public static void main(String[] args) throws IOException {
//        PaymentService paymentService = new WebApiExRatePaymentService();
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment prepare = paymentService.prepare(1L, "USD", new BigDecimal("100.42"));
        System.out.println(prepare);
    }
}
