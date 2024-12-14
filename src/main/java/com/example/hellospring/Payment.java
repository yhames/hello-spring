package com.example.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
                      BigDecimal convertedAmount, LocalDateTime validUntil) {

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
