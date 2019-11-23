package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity

data class Member(
    val id: Int,
    val name: String,
    val imageUrl: ImageContainer,
    val providerId: String,
    val providerUserId: String
) : Entity {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id
}