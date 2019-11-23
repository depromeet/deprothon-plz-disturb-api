package com.depromeet.plzdisturb.deprothonplzdisturbapi.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ConditionalOnWebApplication
class WebMvcConfig(@param:Qualifier("tokenInterceptor") private val tokenInterceptor: HandlerInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/members/login")
            .excludePathPatterns("/swagger-ui.html")
            .excludePathPatterns("/webjars/springfox-swagger-ui/**")
            .excludePathPatterns("/swagger-resources/**")
            .excludePathPatterns("/v2/api-docs")
    }
}
