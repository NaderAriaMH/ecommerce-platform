package com.naderaria.identity.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Value("${token.refresh.signing.key}")
    private String jwtRefreshSigningKey;

    @Value("${token.access.token.expiration}")
    private long accessTokenExpiration;

    @Value("${token.refresh.token.expiration}")
    private long refreshTokenExpiration;



    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, accessTokenExpiration);
    }

    @Override
    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username, accessTokenExpiration);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return "REFRESH_" + generateToken(new HashMap<>(), userDetails, refreshTokenExpiration, jwtRefreshSigningKey);

    }

    @Override
    public String generateRefreshToken(String username) {
        return "REFRESH_" + generateToken(new HashMap<>(), username, refreshTokenExpiration, jwtRefreshSigningKey);

    }
    @Override
    public boolean isTokenValid(String token, String username) {
        final String extractTokenUsername = extractUsername(token);
        return (extractTokenUsername.equals(username)) && !isTokenExpired(token);
    }


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims=extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return generateToken(extraClaims, userDetails, expiration, jwtSigningKey);
    }

    private String generateToken(Map<String, Object> extraClaims, String username, long expiration) {
        return generateToken(extraClaims, username, expiration, jwtSigningKey);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration, String signingKey) {
        return Jwts
                .builder()
                .claims()
                .add(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .and()
                .signWith(getSigningKey(signingKey))
                .compact();
    }

    private String generateToken(Map<String, Object> extraClaims, String username, long expiration, String signingKey) {
        return Jwts
                .builder()
                .claims()
                .add(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .and()
                .signWith(getSigningKey(signingKey))
                .compact();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey(jwtSigningKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private SecretKey getSigningKey(String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
