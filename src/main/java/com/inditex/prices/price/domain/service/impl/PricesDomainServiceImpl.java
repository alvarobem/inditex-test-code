package com.inditex.prices.price.domain.service.impl;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.domain.repository.PricesRepository;
import com.inditex.prices.price.domain.service.PricesDomainService;
import com.inditex.prices.shared.exceptions.FailureReason;
import com.inditex.prices.shared.exceptions.InditexCustomException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PricesDomainServiceImpl implements PricesDomainService {

    private PricesRepository pricesRepository;

    private Validator validator;

    @Override
    public Optional<ProductPriceModel> findProductPriceByFilters(PriceQueryModel priceQuery) {
        checkQuery(priceQuery);
        return pricesRepository.findPricesByFilters(priceQuery).stream().findFirst();
    }

    private void checkQuery(PriceQueryModel priceQueryModel) {
        final var violations = validator.validate(priceQueryModel);
        if (!violations.isEmpty()){
            throw new InditexCustomException("exceptions.invalid-query", FailureReason.BAD_REQUEST);
        }
    }
}
