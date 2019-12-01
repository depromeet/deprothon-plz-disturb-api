package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.api

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
@RunWith(SpringRunner::class)
class LoginIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var kakaoRepository: OAuthRepository

    @Before
    fun setUp() {
        this.mockKakaoMember()
    }

    @Test
    fun login() {
        // given
        val kakaoAccessToken = "kakaoAccessToken"
        val loginRequest = LoginRequest(kakaoAccessToken, "deviceToken")
        // when
        mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginRequest)))
                // then
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").isString)
    }

    private fun mockKakaoMember() {
        val dummy = AccessToken("kakaoAccessToken")
        Mockito.`when`(kakaoRepository.getUserInfo(dummy.value)).thenReturn(
                KakaoUserResponse(
                        1,
                        mapOf(
                                "nickname" to "name",
                                "profile_image" to "profile"

                        )
                )
        )
    }
}
