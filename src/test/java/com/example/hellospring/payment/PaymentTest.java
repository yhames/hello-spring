package com.example.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void createPrepared() {
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock));
        assertThat(payment.convertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment.validUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock));
        assertThat(payment.isValid(clock)).isTrue();
    }

    @Test
    void isValid_만료된_경우() {
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock));
        Clock expiredClock = Clock
                .fixed(Instant.now().plusSeconds(31 * 60), ZoneId.systemDefault());
        assertThat(payment.isValid(expiredClock)).isFalse();
    }
}