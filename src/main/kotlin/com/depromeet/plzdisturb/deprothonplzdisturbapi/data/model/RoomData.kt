package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class RoomData(
    @Id
    @GeneratedValue
    val id: Int?,
    val code: String,
    val name: String
)

fun RoomData.toEntity(members: List<MemberData>) = Room(
    id!!,
    code,
    name,
    members.map { it.toEntity() }
)

fun Room.toDataModel() = RoomData(
    id,
    code,
    name
)
