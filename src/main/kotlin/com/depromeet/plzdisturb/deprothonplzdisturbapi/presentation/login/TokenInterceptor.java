package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login;

import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("tokenInterceptor")
public class TokenInterceptor implements HandlerInterceptor {
    private final JwtFactory jwtFactory;

    public TokenInterceptor(JwtFactory jwtFactory) {
        this.jwtFactory = jwtFactory;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Integer memberId = jwtFactory.decodeToken(request.getHeader("Authorization")).orElse(null);
        if (memberId == null) {
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return false;
        }
        request.setAttribute("memberId", memberId);
        return true;
    }
}

