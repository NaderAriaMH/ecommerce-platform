package com.naderaria.identity.api.dto.role.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResRolePageItemDto(
        Long id,
        String groupName,
        String title,
        String description)
        implements PageItem {
}
