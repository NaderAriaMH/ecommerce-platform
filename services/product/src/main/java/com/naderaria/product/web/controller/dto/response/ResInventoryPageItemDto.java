package com.naderaria.product.web.controller.dto.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResInventoryPageItemDto(Long id, String productName, int quantity, int reservedQuantity)
        implements PageItem {
}