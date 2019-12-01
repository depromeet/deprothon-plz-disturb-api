package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.LoginService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/members/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): LoginResponse {
        val kakaoAccessToken = loginRequest.accessToken
        val accessToken = AccessToken(kakaoAccessToken)
        val (value) = loginService.execute(LoginService.Param(accessToken, loginRequest.deviceToken))
        return LoginResponse(value)
    }
}
