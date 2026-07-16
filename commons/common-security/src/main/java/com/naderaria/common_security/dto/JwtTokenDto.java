package com.naderaria.common_security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record JwtTokenDto(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
}
