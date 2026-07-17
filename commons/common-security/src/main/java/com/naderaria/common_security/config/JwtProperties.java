package com.naderaria.common_security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class JwtProperties {

    private String signingKey;

    private String refreshSigningKey;

    private long accessTokenExpiration;

    private long refreshTokenExpiration;

}