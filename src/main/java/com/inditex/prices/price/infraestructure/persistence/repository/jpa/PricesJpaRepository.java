package com.inditex.prices.price.infraestructure.persistence.repository.jpa;

import com.inditex.prices.price.infraestructure.persistence.entity.ProductPriceEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PricesJpaRepository extends JpaRepository<ProductPriceEntity, Long>, JpaSpecificationExecutor<ProductPriceEntity> {


    interface Specs {

        static Specification<ProductPriceEntity> startDateGreaterThan(LocalDateTime date){

            return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), date));
        }

        static Specification<ProductPriceEntity> endDateLowerThan(LocalDateTime date){

            return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), date));
        }

        static Specification<ProductPriceEntity> brandIdEqualTo(Long brandId){

            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brandId"), brandId));
        }
        static Specification<ProductPriceEntity> productEqualTo(Long productId){

            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), productId));
        }

        static Specification<ProductPriceEntity> orderByPriority(Specification<ProductPriceEntity> spec){
            return ((root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.desc(root.get("priority")));
                return spec.toPredicate(root, query, criteriaBuilder);
            });
        }


    }
}
