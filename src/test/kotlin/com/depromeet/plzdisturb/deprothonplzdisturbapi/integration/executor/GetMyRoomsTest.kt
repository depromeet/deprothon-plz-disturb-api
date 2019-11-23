package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoom
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMyRooms
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.Login
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
class GetMyRoomsTest {

    @Autowired
    private lateinit var createRoom: CreateRoom

    @Autowired
    private lateinit var getMyRooms: GetMyRooms

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
        val room = createRoom.execute(
            CreateRoom.Param("group", 1)
        )
        // when
        val target = getMyRooms.execute(
            GetMyRooms.Param(1)
        )
        // then
        assertThat(target).isEqualTo(listOf(room))
    }
}