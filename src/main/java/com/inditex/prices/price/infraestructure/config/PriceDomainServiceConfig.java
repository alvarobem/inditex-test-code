package com.inditex.prices.price.infraestructure.config;

import com.inditex.prices.price.domain.repository.PricesRepository;
import com.inditex.prices.price.domain.service.impl.PricesDomainServiceImpl;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PriceDomainServiceConfig {

    private Validator validator;

    private PricesRepository pricesRepository;

    @Bean
    public PricesDomainServiceImpl pricesDomainService(){
        return new PricesDomainServiceImpl(pricesRepository, validator);
    }
}
