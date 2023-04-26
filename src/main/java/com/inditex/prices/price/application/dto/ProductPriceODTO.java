package com.inditex.prices.price.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductPriceODTO(@NotNull LocalDateTime startDate,
                               @NotNull LocalDateTime endDate,
                               @NotNull Double pvp,
                               @NotNull Integer priceList,
                               @NotNull Long productId,
                               @NotNull Long brandId) {
}
