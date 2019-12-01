package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.room

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member.MemberAssembler
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class RoomAssembler(
    private val memberAssembler: MemberAssembler
) {

    fun toRoomResponse(room: Room): RoomResponse {
        return RoomResponse(
            room.id,
            room.code,
            room.name,
            room.members.stream()
                .map { member -> memberAssembler.toMemberResponse(member) }
                .collect(Collectors.toList())
        )
    }
}