package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.api

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.Response
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.LoginRequest
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.LoginResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member.MemberResponse
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
@RunWith(SpringRunner::class)
class GetMeIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var kakaoRepository: OAuthRepository

    private lateinit var accessToken: String

    @Before
    fun setUp() {
        this.mockKakaoMember("kakaoAccessToken", 1L)

        val loginRequest = LoginRequest("kakaoAccessToken", "deviceToken")
        val mvcResult = mockMvc.perform(post("/api/members/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(loginRequest)))
            .andReturn()
        val loginResponse: LoginResponse  = objectMapper.readValue(mvcResult.response.contentAsString)
        accessToken = loginResponse.accessToken
    }

    @Test
    fun getMe() {
        // given
        // when
        val mvcResult = mockMvc.perform(get("/api/members/me")
            .header("Authorization", "Bearer $accessToken"))
            // then 1
            .andExpect(status().isOk)
            .andReturn()
        // then 2
        val getMeResponse: Response<MemberResponse> = objectMapper.readValue(mvcResult.response.contentAsString)
        assertThat(getMeResponse.data).isNotNull
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
}