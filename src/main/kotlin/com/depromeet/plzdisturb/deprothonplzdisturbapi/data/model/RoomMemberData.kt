package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class RoomMemberData(
    @Id
    val id: Int?,
    @ManyToOne
    val member: MemberData,
    @ManyToOne
    val room: RoomData
)
