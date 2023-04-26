package com.inditex.prices.price.shared.mapper;

import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;
import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.infraestructure.api.dto.PriceQueryRQDTO;
import com.inditex.prices.price.infraestructure.api.dto.ProductPriceRSDTO;
import com.inditex.prices.price.infraestructure.persistence.entity.ProductPriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    PriceQueryIDTO toIDTO(PriceQueryRQDTO priceQueryRQDTO);

    ProductPriceRSDTO toRSDTO(ProductPriceODTO productPriceODTO);

    PriceQueryModel toModel(PriceQueryIDTO priceQuery);

    ProductPriceODTO toODTO(ProductPriceModel productPrice);

    @Mapping(source = "price", target = "pvp")
    ProductPriceModel toModel(ProductPriceEntity productPrice);
}
