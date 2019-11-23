package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login;

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.Login;
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    @PostMapping("/api/members/login")
    public LoginResponse login(LoginRequest loginRequest) {
        String kakaoAccessToken = loginRequest.getAccessToken();
        AccessToken accessToken = new AccessToken(kakaoAccessToken);
        AccessToken responseAccessToken = login.execute(new Login.Param(accessToken, loginRequest.getDeviceToken()));
        return new LoginResponse(responseAccessToken.getValue());
    }
}
