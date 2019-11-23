package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import javax.persistence.*

@Entity
data class MemberData(
    @Id
    @GeneratedValue
    val id: Int?,
    val name: String,
    @Embedded
    val imageContainer: ImageContainer
)

fun MemberData.toEntity() = Member(
    id!!,
    name,
    imageContainer
)