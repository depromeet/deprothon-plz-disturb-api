package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import javax.persistence.Id

data class MemberData(
    @Id
    val id: Int?,
    val name: String,
    val imageUrl: ImageContainer
)

fun MemberData.toEntity() = Member(
    id!!,
    name,
    imageUrl
)