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
        val imageUrl: String?
)

fun MemberData.toEntity() = Member(
        id!!,
        name,
        imageUrl?.let {
            ImageContainer.Image(it)
        } ?: ImageContainer.NONE
)