package com.example.hellospring.payment;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);
}
