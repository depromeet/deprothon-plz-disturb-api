package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoomService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.LoginService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
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
class CreateRoomServiceTest {
    @Autowired
    private lateinit var createRoomService: CreateRoomService

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
        val name = "name"
        // when
        val room = createRoomService.execute(
            CreateRoomService.Param(name, 1)
        )
        // then
        assertThat(room.name).isEqualTo("name")
        assertThat(room.members).isNotEmpty
    }
}