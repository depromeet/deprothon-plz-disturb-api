package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity

data class Room(
    val id: Int,
    val code: String,
    val name: String,
    val members: List<Member>
) : Entity {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Room

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id
}