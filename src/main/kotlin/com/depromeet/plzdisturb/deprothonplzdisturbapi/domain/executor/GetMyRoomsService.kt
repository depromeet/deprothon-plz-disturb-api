package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetMyRoomsService(
    private val memberRepository: MemberRepository,
    private val roomMemberRepository: RoomMemberRepository
) : Executor<GetMyRoomsService.Param, List<Room>> {

    @Transactional
    override fun execute(param: Param): List<Room> = param.let { (memberId) ->
        val member = memberRepository.get(memberId)
        roomMemberRepository.getRooms(member)
    }

    data class Param(
        val memberId: Int
    ) : Executor.Param
}