package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
class GetMyRooms(
    private val memberRepository: MemberRepository,
    private val roomMemberRepository: RoomMemberRepository
) : Executor<GetMyRooms.Param, List<Room>> {

    override fun execute(param: Param): List<Room> = param.let { (memberId) ->
        val member = memberRepository.get(memberId)
        roomMemberRepository.getRooms(member)
    }

    data class Param(
        val memberId: Int
    ) : Executor.Param
}