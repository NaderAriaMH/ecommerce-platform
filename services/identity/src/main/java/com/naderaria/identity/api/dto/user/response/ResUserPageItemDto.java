package com.naderaria.identity.api.dto.user.response;

import com.naderaria.common_core.dto.response.PageItem;

public record ResUserPageItemDto(
        Long id,
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        String city,
        String phoneNumber,
        boolean enabled)
        implements PageItem {
}
