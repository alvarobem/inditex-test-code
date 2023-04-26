package com.inditex.prices.price.domain.service;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;

import java.util.Optional;

public interface PricesDomainService {

    Optional<ProductPriceModel> findProductPriceByFilters(PriceQueryModel priceQuery);
}
