package com.inditex.prices.price.infraestructure.persistence;

import com.inditex.prices.price.infraestructure.persistence.entity.ProductPriceEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSpecs {

    public Specification<ProductPriceEntity> startDateGreaterThan(LocalDateTime date){

        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), date));
    }

    public Specification<ProductPriceEntity> endDateLowerThan(LocalDateTime date){

        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), date));
    }

    public Specification<ProductPriceEntity> brandIdEqualTo(Long brandId){

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brandId"), brandId));
    }
    public Specification<ProductPriceEntity> productEqualTo(Long productId){

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), productId));
    }

    public Specification<ProductPriceEntity> orderByPriority(Specification<ProductPriceEntity> spec){
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("priority")));
            return spec.toPredicate(root, query, criteriaBuilder);
        });
    }
}
