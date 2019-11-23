package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.Login
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(private val login: Login) {

    @PostMapping("/api/members/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        val kakaoAccessToken = loginRequest.accessToken
        val accessToken = AccessToken(kakaoAccessToken)
        val (value) = login.execute(Login.Param(accessToken, loginRequest.deviceToken))
        return LoginResponse(value)
    }
}
