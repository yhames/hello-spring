package com.example.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class SimpleExRatePaymentService extends PaymentService {

    @Override
    BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return new BigDecimal("1100.00");
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
