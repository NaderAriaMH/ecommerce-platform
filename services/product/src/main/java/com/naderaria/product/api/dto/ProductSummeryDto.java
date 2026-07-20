package com.naderaria.product.api.dto;

import java.math.BigDecimal;

public record ProductSummeryDto(Long id, String name, BigDecimal price) {
}