package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login

import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

@Component("tokenInterceptor")
class TokenInterceptor(private val jwtFactory: JwtFactory) : HandlerInterceptor {

    @Throws(IOException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val memberId = jwtFactory.decodeToken(request.getHeader("Authorization")).orElse(null)
        if (memberId == null) {
            response.contentType = MediaType.TEXT_PLAIN_VALUE
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized")
            return false
        }
        request.setAttribute("memberId", memberId)
        return true
    }
}

