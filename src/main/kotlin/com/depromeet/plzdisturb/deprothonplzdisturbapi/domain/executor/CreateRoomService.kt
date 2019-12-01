package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CreateRoomService(
    private val roomRepository: RoomRepository,
    private val memberRepository: MemberRepository,
    private val roomMemberRepository: RoomMemberRepository
) : Executor<CreateRoomService.Param, Room> {

    @Transactional
    override fun execute(param: Param): Room = param.let { (name, memberId) ->
        val room = roomRepository.add(
            name,
            UUID.randomUUID().toString().substring(0, 6)
        )

        val member = memberRepository.get(memberId)

        roomMemberRepository.add(room, member)

        return@let room.copy(
            members = listOf(member)
        )
    }

    data class Param(
        val name: String,
        val memberId: Int
    ) : Executor.Param
}