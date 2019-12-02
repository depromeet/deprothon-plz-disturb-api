package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.*
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class JoinRoomTest {

    @Autowired
    private lateinit var jwtFactory: JwtFactory

    @Autowired
    private lateinit var createRoom: CreateRoom

    @Autowired
    private lateinit var joinRoom: JoinRoom

    @MockBean
    private lateinit var kakaoRepository: OAuthRepository

    @Autowired
    private lateinit var login: Login

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
        val token = login.execute(
            Login.Param(
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
        val token2 = login.execute(
            Login.Param(
                dummy2,
                "device"
            )
        )
        val memberId2 = jwtFactory.decodeToken("bearer ${token2.value}").get()
        val room = createRoom.execute(
            CreateRoom.Param("group", memberId)
        )
        // when
        val target = joinRoom.execute(
            JoinRoom.Param(memberId2, room.code)
        )
        // then
        assertThat(target).isEqualTo(room)
    }
}