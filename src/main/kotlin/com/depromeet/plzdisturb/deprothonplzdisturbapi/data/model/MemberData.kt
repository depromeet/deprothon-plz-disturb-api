package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer

data class MemberData(
    val id: Int?,
    val name: String,
    val imageUrl: ImageContainer
)