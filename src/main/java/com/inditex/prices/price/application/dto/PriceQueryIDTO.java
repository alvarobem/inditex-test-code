package com.inditex.prices.price.application.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceQueryIDTO(LocalDateTime applicationDate, Long productId, Long brandId) {
}
