package com.naderaria.identity.application.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(String username);

    String generateRefreshToken(UserDetails userDetails);

    String generateRefreshToken(String username);

    boolean isTokenValid(String token, String username);

}
