package com.inditex.prices.price.domain.repository;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;

import java.util.Optional;

public interface PricesRepository {

    Optional<ProductPriceModel> findPriceByFilters(PriceQueryModel priceQuery);
}
