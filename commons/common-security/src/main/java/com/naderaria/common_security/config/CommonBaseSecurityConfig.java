package com.naderaria.common_security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface CommonBaseSecurityConfig {

    void configure(HttpSecurity http) throws Exception;
}
