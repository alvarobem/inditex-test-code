package com.inditex.prices.price.domain.repository;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;

import java.util.List;

public interface PricesRepository {

    List<ProductPriceModel> findPricesByFilters(PriceQueryModel priceQuery);
}
