package com.naderaria.product.api.dto.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResCategoryPageItemDto(
        Long id,
        Long parentId,
        String parentName,
        String name,
        String description,
        boolean active,
        int sortOrder)
        implements PageItem {
}






