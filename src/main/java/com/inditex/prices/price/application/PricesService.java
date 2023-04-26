package com.inditex.prices.price.application;

import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;


public interface PricesService {

    ProductPriceODTO findPriceByFilters(PriceQueryIDTO priceQueryIDTO);
}
