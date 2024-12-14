package com.example.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        testAmount(new BigDecimal("1100.00"), new BigDecimal("11000.00"));
        testAmount(new BigDecimal("700.00"), new BigDecimal("7000.00"));
        testAmount(new BigDecimal("3000.00"), new BigDecimal("30000.00"));

        // 원화 환산금액 유효시간 계산
//        assertThat(payment.validUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.validUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        ExRateProviderStub exRateProviderStub = new ExRateProviderStub(exRate);
        PaymentService paymentService = new PaymentService(exRateProviderStub);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다
        assertThat(payment.exRate()).isEqualByComparingTo(exRate);
        // 원화 환산금액 계산
        assertThat(payment.convertedAmount()).isEqualTo(convertedAmount);
    }
}
