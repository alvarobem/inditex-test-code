package com.inditex.prices.price.infraestructure.api;

import com.inditex.prices.price.infraestructure.api.dto.PriceQueryRQDTO;
import com.inditex.prices.price.infraestructure.api.dto.ProductPriceRSDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface PricesController {

    @GetMapping(path = "/prices")
    ResponseEntity<ProductPriceRSDTO> getPrices(@Valid PriceQueryRQDTO priceQueryRQDTO);
}
