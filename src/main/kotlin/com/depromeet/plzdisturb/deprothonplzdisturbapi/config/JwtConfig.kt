package com.depromeet.plzdisturb.deprothonplzdisturbapi.config

import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
    @Value("\${jwt.tokenIssuer:defaultTokenIssuer}")
    private lateinit var tokenIssuer: String
    @Value("\${jwt.tokenSigningKey:defaultTokenSigningKey}")
    private lateinit var tokenSigningKey: String

    @Bean
    fun jwtFactory(): JwtFactory {
        return JwtFactory(tokenIssuer, tokenSigningKey)
    }
}