package com.naderaria.product.api.dto.response;

import com.naderaria.common_core.dto.response.PageItem;
import com.naderaria.product.infratructure.domain.model.ProductStatusType;

import java.math.BigDecimal;

public record ResProductPageItemDto(
        Long id,
        String name,
        String description,
        String categoryName,
        BigDecimal price,
        ProductStatusType statusType,
        int stockQuantity) implements PageItem {
}