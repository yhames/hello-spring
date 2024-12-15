package com.example.hellospring;

import com.example.hellospring.api.ApiTemplate;
import com.example.hellospring.api.ErApiExRateExtractor;
import com.example.hellospring.api.SimpleApiExecutor;
import com.example.hellospring.exrate.CachedExRateProvider;
import com.example.hellospring.exrate.RestTemplateExRateProvider;
import com.example.hellospring.payment.ExRateProvider;
import com.example.hellospring.exrate.WebApiExRateProvider;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(restTemplateExRateProvider());
    }

    @Bean
    public WebApiExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public RestTemplateExRateProvider restTemplateExRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
