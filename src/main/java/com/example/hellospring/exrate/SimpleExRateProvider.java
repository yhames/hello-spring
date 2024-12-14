package com.example.hellospring.exrate;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return new BigDecimal("1100.00");
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
