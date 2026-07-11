package com.naderaria.identity.api.dto.user.response;

import com.naderaria.identity.api.dto.contact_info.response.ResContactInfoDto;
import com.naderaria.identity.api.dto.location_info.response.ResLocationInfoDto;

public record ResUserDto(
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        ResLocationInfoDto locationInfo,
        ResContactInfoDto contactInfoDto) {
}
