package com.example.hellospring.exrate;

import com.example.hellospring.api.*;
import com.example.hellospring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
