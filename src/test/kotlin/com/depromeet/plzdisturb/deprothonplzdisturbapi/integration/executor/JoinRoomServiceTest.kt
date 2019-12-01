package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoomService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.JoinRoomService
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
class JoinRoomServiceTest {

    @Autowired
    private lateinit var jwtFactory: JwtFactory

    @Autowired
    private lateinit var createRoomService: CreateRoomService

    @Autowired
    private lateinit var joinRoomService: JoinRoomService

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
        val dummy2 = AccessToken("ss2")
        Mockito.`when`(kakaoRepository.getUserInfo(dummy2.value)).thenReturn(
            KakaoUserResponse(
                2,
                mapOf(
                    "nickname" to "name",
                    "profile_image" to "profile"

                )
            )
        )
        val token2 = loginService.execute(
            LoginService.Param(
                dummy2,
                "device"
            )
        )
        val memberId2 = jwtFactory.decodeToken("bearer ${token2.value}").get()
        val room = createRoomService.execute(
            CreateRoomService.Param("group", memberId)
        )
        // when
        val target = joinRoomService.execute(
            JoinRoomService.Param(memberId2, room.code)
        )
        // then
        assertThat(target).isEqualTo(room)
    }
}