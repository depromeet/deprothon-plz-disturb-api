package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class RoomMemberData(
    @Id
    @GeneratedValue
    val id: Int?,
    @ManyToOne
    val member: MemberData,
    @ManyToOne
    val room: RoomData
)
