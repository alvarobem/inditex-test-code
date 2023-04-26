package com.inditex.prices.price.infraestructure.persistence.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceQueryEntity(@NotNull LocalDateTime applicationDate, @NotNull Long productId, @NotNull Long brandId) {
}
