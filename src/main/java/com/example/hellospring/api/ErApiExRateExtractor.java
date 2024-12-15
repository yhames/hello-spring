package com.example.hellospring.api;

import com.example.hellospring.exrate.ExRateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor {

    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = objectMapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
