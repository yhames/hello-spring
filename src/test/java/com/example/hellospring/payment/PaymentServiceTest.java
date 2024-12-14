package com.example.hellospring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;

public class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void convertedAmount() throws IOException {
        testAmount(new BigDecimal("1100.00"), new BigDecimal("11000.00"), clock);
        testAmount(new BigDecimal("700.00"), new BigDecimal("7000.00"), clock);
        testAmount(new BigDecimal("3000.00"), new BigDecimal("30000.00"), clock);
    }

    @Test
    void validUntil() throws IOException {
        ExRateProviderStub exRateProviderStub = new ExRateProviderStub(BigDecimal.valueOf(1000));
        PaymentService paymentService = new PaymentService(exRateProviderStub, clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.validUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        ExRateProviderStub exRateProviderStub = new ExRateProviderStub(exRate);
        PaymentService paymentService = new PaymentService(exRateProviderStub, clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다
        assertThat(payment.exRate()).isEqualByComparingTo(exRate);
        // 원화 환산금액 계산
        assertThat(payment.convertedAmount()).isEqualTo(convertedAmount);
    }
}
