package com.inditex.prices.price.infraestructure.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public record PriceQueryRQDTO(@NotNull  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                              @NotNull Long productId,
                              @NotNull Long brandId) {

    @Builder
    public PriceQueryRQDTO{}
}
