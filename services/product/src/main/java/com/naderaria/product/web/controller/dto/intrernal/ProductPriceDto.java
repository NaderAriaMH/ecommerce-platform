package com.naderaria.product.web.controller.dto.intrernal;

import java.math.BigDecimal;

public record ProductPriceDto(Long id, BigDecimal finalPrice) {
}
