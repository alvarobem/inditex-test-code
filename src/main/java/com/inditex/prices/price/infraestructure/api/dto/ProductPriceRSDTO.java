package com.inditex.prices.price.infraestructure.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record ProductPriceRSDTO(@NotNull LocalDateTime startDate,
                                @NotNull LocalDateTime endDate,
                                @NotNull Double pvp,
                                @NotNull Integer priceList,
                                @NotNull Long productId,
                                @NotNull Long brandId) {
}
