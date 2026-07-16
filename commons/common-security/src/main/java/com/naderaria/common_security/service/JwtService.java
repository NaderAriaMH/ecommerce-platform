package com.naderaria.common_security.service;

import com.naderaria.common_security.dto.CurrentUserDto;
import com.naderaria.common_security.dto.JwtTokenDto;

public interface JwtService {

    String generateToken(CurrentUserDto currentUserDto);

    String extractUsername(String token);

    String generateRefreshToken(CurrentUserDto currentUserDto);

    boolean isTokenValid(String token, String username);

    JwtTokenDto extractJwtTokenDot(String token);
}
