package com.example.hellospring.exrate;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CachedExRateProvider implements ExRateProvider{

    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheValidUntil;

    public CachedExRateProvider(ExRateProvider exRateProvider) {
        this.target = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (cachedExRate == null || cacheValidUntil.isBefore(LocalDateTime.now())) {
            cachedExRate = target.getExRate(currency);
            cacheValidUntil = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cache Updated");
        }
        return cachedExRate;
    }
}
