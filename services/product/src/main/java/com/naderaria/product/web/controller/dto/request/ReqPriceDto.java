package com.naderaria.product.web.controller.dto.request;

import java.math.BigDecimal;

public record ReqPriceDto(BigDecimal amount, double discount, Long currencyId) {
}