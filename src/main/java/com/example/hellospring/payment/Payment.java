package com.example.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Payment {
    private final Long orderId;
    private final String currency;
    private final BigDecimal foreignCurrencyAmount;
    private final BigDecimal exRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
                   BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
                                         BigDecimal exRate, LocalDateTime now) {
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = now.plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(validUntil);
    }

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

    public Long orderId() {
        return orderId;
    }

    public String currency() {
        return currency;
    }

    public BigDecimal foreignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal exRate() {
        return exRate;
    }

    public BigDecimal convertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime validUntil() {
        return validUntil;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Payment) obj;
        return Objects.equals(this.orderId, that.orderId) &&
                Objects.equals(this.currency, that.currency) &&
                Objects.equals(this.foreignCurrencyAmount, that.foreignCurrencyAmount) &&
                Objects.equals(this.exRate, that.exRate) &&
                Objects.equals(this.convertedAmount, that.convertedAmount) &&
                Objects.equals(this.validUntil, that.validUntil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}
