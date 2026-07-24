package com.naderaria.product.web.controller.dto.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResCurrencyPageItemDto(Long id, String code, String name) implements PageItem {
}