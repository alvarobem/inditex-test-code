package com.inditex.prices.price.infraestructure.persistence.repository;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.domain.repository.PricesRepository;
import com.inditex.prices.price.infraestructure.persistence.PriceSpecs;
import com.inditex.prices.price.infraestructure.persistence.entity.ProductPriceEntity;
import com.inditex.prices.price.infraestructure.persistence.repository.jpa.PricesJpaRepository;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PricesRepositoryImpl implements PricesRepository {

    private final PricesJpaRepository jpaRepository;

    private final PriceMapper mapper;

    private final PriceSpecs priceSpecs;

    @Override
    public Optional<ProductPriceModel> findPriceByFilters(PriceQueryModel priceQuery) {
        var query = buildQuery(priceQuery);
        query  = PricesJpaRepository.Specs.orderByPriority(query);
        return jpaRepository.findOne(query).map(mapper::toModel);
    }

    private Specification<ProductPriceEntity> buildQuery(PriceQueryModel priceQuery){

        return priceSpecs.productEqualTo(priceQuery.productId())
                .and(priceSpecs.startDateLowerThan(priceQuery.applicationDate()))
                .and(priceSpecs.endDateGreaterThan(priceQuery.applicationDate()))
                .and(priceSpecs.brandIdEqualTo(priceQuery.brandId()));
    }

}
