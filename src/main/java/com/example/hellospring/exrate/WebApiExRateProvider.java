package com.example.hellospring.exrate;

import com.example.hellospring.api.ApiExecutor;
import com.example.hellospring.api.ErApiExRateExtractor;
import com.example.hellospring.api.ExRateExtractor;
import com.example.hellospring.api.SimpleApiExecutor;
import com.example.hellospring.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
