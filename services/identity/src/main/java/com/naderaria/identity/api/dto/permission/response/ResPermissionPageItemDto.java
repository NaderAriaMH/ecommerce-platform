package com.naderaria.identity.api.dto.permission.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResPermissionPageItemDto(
        Long id,
        String operation,
        String targetType,
        String targetScope,
        String title)
        implements PageItem {
}
