package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetRoomsService(
    private val roomRepository: RoomRepository,
    private val memberRepository: MemberRepository,
    private val roomMemberRepository: RoomMemberRepository
) : Executor<GetRoomsService.Param, Room> {

    @Transactional
    override fun execute(param: Param): Room = param.let { (memberId, code) ->
        val room = roomRepository.get(code)
        val member = memberRepository.get(memberId)
        val roomMembers = roomMemberRepository.getMembers(room)
        if (roomMembers.contains(member))
            room.copy(
                members = roomMembers
            )
        else throw IllegalArgumentException("member not exist")
    }

    data class Param(
        val memberId: Int,
        val code: String
    ) : Executor.Param
}