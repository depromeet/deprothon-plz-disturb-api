package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoApiFailedException
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Repository
class KakaoRepository(
        private val restTemplate: RestTemplate
) : OAuthRepository {

    @Throws(IllegalStateException::class)
    override fun getUserInfo(accessToken: String): KakaoUserResponse {
        val requestUrl = UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me")
                .build(true)
                .toUri()

        val httpHeaders = HttpHeaders()
        httpHeaders.add("Authorization", "Bearer $accessToken")

        val httpEntity = HttpEntity<Any>(httpHeaders)

        val responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, KakaoUserResponse::class.java)
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw KakaoApiFailedException("Failed to get User details from kakao api. status:" + responseEntity.getStatusCode())
        }
        return responseEntity.body ?: throw IllegalStateException()
    }
}