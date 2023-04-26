package com.inditex.prices.price.application.imp;

import com.inditex.prices.price.application.PricesService;
import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;
import com.inditex.prices.price.domain.service.PricesDomainService;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import com.inditex.prices.shared.exceptions.FailureReason;
import com.inditex.prices.shared.exceptions.InditexCustomException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PricesServiceImpl implements PricesService {

    private final PriceMapper mapper;

    private final PricesDomainService pricesDomainService;

    @Override
    @Transactional
    public ProductPriceODTO findPriceByFilters(PriceQueryIDTO priceQueryIDTO) {

        final var priceQueryModel = mapper.toModel(priceQueryIDTO);
        return pricesDomainService.findProductPriceByFilters(priceQueryModel)
                .map(mapper::toODTO)
                .orElseThrow(()-> new InditexCustomException("exceptions.price-not-found", FailureReason.NOT_FOUND));
    }
}
