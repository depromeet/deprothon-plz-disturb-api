package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity

import javax.persistence.Id

data class Member(
    @Id
    val id: Int,
    val name: String,
    val imageUrl: String
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