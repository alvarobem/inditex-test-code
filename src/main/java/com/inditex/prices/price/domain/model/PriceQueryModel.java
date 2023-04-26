package com.inditex.prices.price.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceQueryModel(@NotNull LocalDateTime applicationDate, @NotNull Long productId, @NotNull Long brandId) {
}
