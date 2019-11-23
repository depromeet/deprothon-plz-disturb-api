package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room

interface RoomMemberRepository : Repository {

    fun add(room: Room, member: Member): Pair<Room, Member>
    fun getMembers(room: Room): List<Member>
    fun getRooms(member: Member): List<Room>
}
