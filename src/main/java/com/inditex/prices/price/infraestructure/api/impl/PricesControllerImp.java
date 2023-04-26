package com.inditex.prices.price.infraestructure.api.impl;

import com.inditex.prices.price.application.PricesService;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import com.inditex.prices.price.infraestructure.api.dto.PriceQueryRQDTO;
import com.inditex.prices.price.infraestructure.api.PricesController;
import com.inditex.prices.price.infraestructure.api.dto.ProductPriceRSDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PricesControllerImp implements PricesController {

    private final PriceMapper mapper;

    private final PricesService pricesService;

    public ResponseEntity<ProductPriceRSDTO> getPrices(@Valid PriceQueryRQDTO priceRQRDTO){
        final var priceIDTO = mapper.toIDTO(priceRQRDTO);
        final var productPriceODTO = pricesService.findPriceByFilters(priceIDTO);
        final var productPriceRSDTO = mapper.toRSDTO(productPriceODTO);
        return ResponseEntity.ok(productPriceRSDTO);
    }
}
