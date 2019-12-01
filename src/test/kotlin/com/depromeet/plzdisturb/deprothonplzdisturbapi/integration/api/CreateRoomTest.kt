package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.api

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.Request.Request
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.response.Response
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.LoginRequest
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.LoginResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.room.RoomResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.transaction.annotation.Transactional

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
@RunWith(SpringRunner::class)
class CreateRoomTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var kakaoRepository: OAuthRepository

    private lateinit var accessToken: String
    private val jacksonObjectMapper = jacksonObjectMapper()

    @Before
    fun setUp() {
        this.mockKakaoMember("kakaoAccessToken", 1L)
        val loginResponse = this.login("kakaoAccessToken")
        accessToken = loginResponse.accessToken
    }

    @Test
    fun createRoom() {
        // given
        val roomRequest = Request("roomName")
        // when
        val mvcResult = mockMvc.perform(post("/api/rooms")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(roomRequest)))
            .andReturn()
        // then
        assertThat(mvcResult.response.status).isEqualTo(HttpStatus.OK.value())
        val createRoomResponse: Response<RoomResponse> = jacksonObjectMapper.readValue(mvcResult.response.contentAsString)
        assertThat(createRoomResponse.data).isNotNull
    }

    private fun mockKakaoMember(kakaoAccessToken: String, id: Long) {
        val dummy = AccessToken(kakaoAccessToken)
        Mockito.`when`(kakaoRepository.getUserInfo(dummy.value)).thenReturn(
            KakaoUserResponse(
                id,
                mapOf(
                    "nickname" to "name",
                    "profile_image" to "profile"

                )
            )
        )
    }

    private fun login(kakaoAccessToken: String): LoginResponse {
        val loginRequest = LoginRequest(kakaoAccessToken, "deviceToken")
        val mvcResult = mockMvc.perform(post("/api/members/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(loginRequest)))
            .andReturn()
        return objectMapper.readValue(mvcResult.response.contentAsByteArray, LoginResponse::class.java)
    }
}
