package com.example.hellospring.payment;

import com.example.hellospring.TestPaymentConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
public class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Autowired
    Clock clock;

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        assertThat(payment.exRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment.convertedAmount()).isEqualTo(BigDecimal.valueOf(10_000));

        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        assertThat(payment2.exRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        assertThat(payment2.convertedAmount()).isEqualTo(BigDecimal.valueOf(5_000));
    }

    @Test
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        assertThat(payment.validUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }
}
