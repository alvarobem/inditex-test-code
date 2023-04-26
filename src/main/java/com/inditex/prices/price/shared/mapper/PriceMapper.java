package com.inditex.prices.price.shared.mapper;

import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;
import com.inditex.prices.price.infraestructure.api.dto.PriceQueryRQDTO;
import com.inditex.prices.price.infraestructure.api.dto.ProductPriceRSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    PriceQueryIDTO toIDTO(PriceQueryRQDTO priceQueryRQDTO);

    ProductPriceRSDTO toRSDTO(ProductPriceODTO productPriceODTO);
}
