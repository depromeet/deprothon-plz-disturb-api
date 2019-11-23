package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.MemberData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.RoomData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.RoomMemberData
import org.springframework.data.jpa.repository.JpaRepository

interface RoomMemberJpaRepository : JpaRepository<RoomMemberData, Int> {

    fun findByRoom(room: RoomData): List<RoomMemberData>
    fun findByMember(member: MemberData): List<RoomMemberData>
}