package com.depromeet.plzdisturb.deprothonplzdisturbapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.tokenIssuer:defaultTokenIssuer}")
    private String tokenIssuer;
    @Value("${jwt.tokenSigningKey:defaultTokenSigningKey}")
    private String tokenSigningKey;

    @Bean
    public JwtFactory jwtFactory() {
        return new JwtFactory(tokenIssuer, tokenSigningKey);
    }
}