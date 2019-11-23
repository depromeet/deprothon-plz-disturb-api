package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class MemberData(
    @Id
    @GeneratedValue
    val id: Int?,
    val name: String,
    val imageUrl: String?,
    val providerId: String,
    val providerUserId: String
)

fun MemberData.toEntity() = Member(
    id!!,
    name,
    imageUrl?.let {
        ImageContainer.Image(it)
    } ?: ImageContainer.NONE,
    providerId,
    providerUserId
)

fun Member.toDataModel() = MemberData(
    id,
    name,
    when (imageUrl) {
        is ImageContainer.Image -> imageUrl.url
        ImageContainer.NONE -> null
    },
    providerId,
    providerUserId
)