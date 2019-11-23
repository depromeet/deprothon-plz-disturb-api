package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.kakao

data class KakaoUserResponse(
        var id: Long,
        var properties: Map<String, String>
) {
    fun getUserName(): String {
        return this.properties.getOrDefault("nickname", "")
    }

    fun getProfileImage(): String {
        return this.properties.getOrDefault("profile_image", "")
    }
}

