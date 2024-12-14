package com.example.hellospring;

import java.math.BigDecimal;

public class SimpleExRatePaymentService extends PaymentService {

    @Override
    BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return new BigDecimal("1100.00");
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
