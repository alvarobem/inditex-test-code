package com.inditex.prices.shared.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorRSDTO (String message, Integer code) {
}
