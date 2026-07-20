package com.naderaria.product.api.dto.response;

import java.math.BigDecimal;

public record ResPriceDto(Long id, BigDecimal amount, Integer discount, Long currencyId, String currencyName) {
}