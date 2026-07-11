package com.naderaria.identity.api.dto.role_permission.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResRolePermissionPageItemDto(
        Long id,
        String groupName,
        String roleTitle,
        String permissionTitle)
        implements PageItem {
}





