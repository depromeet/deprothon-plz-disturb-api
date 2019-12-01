package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member

data class MemberResponse(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val providerId: String,
    val providerUserId: String
)
