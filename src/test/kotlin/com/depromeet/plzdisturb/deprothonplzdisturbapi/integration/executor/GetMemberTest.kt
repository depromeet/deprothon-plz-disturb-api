package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMemberService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.LoginService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class GetMemberTest {

    @Autowired
    private lateinit var jwtFactory: JwtFactory

    @Autowired
    private lateinit var getMember: GetMemberService

    @MockBean
    private lateinit var kakaoRepository: OAuthRepository

    @Autowired
    private lateinit var loginService: LoginService

    @Test
    fun test_execute() {
        // given
        val dummy = AccessToken("ss")
        Mockito.`when`(kakaoRepository.getUserInfo(dummy.value)).thenReturn(
            KakaoUserResponse(
                1,
                mapOf(
                    "nickname" to "name",
                    "profile_image" to "profile"

                )
            )
        )
        val token = loginService.execute(
            LoginService.Param(
                dummy,
                "device"
            )
        )
        val memberId = jwtFactory.decodeToken("bearer ${token.value}").get()
        // when
        val target = getMember.execute(
            GetMemberService.Param(memberId)
        )
        // then
        assertThat(target.id).isEqualTo(memberId)
    }
}