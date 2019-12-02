package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
class JoinRoom(
    private val roomRepository: RoomRepository,
    private val memberRepository: MemberRepository,
    private val roomMemberRepository: RoomMemberRepository
) : Executor<JoinRoom.Param, Room> {

    override fun execute(param: Param): Room = param.let { (memberId, code) ->
        val room = roomRepository.get(code)
        val member = memberRepository.get(memberId)
        roomMemberRepository.add(room, member)
        room.copy(
            members = roomMemberRepository.getMembers(room)
        )
    }

    data class Param(
        val memberId: Int,
        val code: String
    ) : Executor.Param
}