package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao.KakaoUserResponse

interface OAuthRepository : Repository {
    fun getUserInfo(accessToken: String): KakaoUserResponse
}