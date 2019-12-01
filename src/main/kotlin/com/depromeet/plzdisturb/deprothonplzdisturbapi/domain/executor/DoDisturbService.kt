package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.FCMRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DoDisturbService(
    private val repository: FCMRepository,
    private val memberRepository: MemberRepository
) : Executor<DoDisturbService.Param, Unit> {

    @Transactional
    override fun execute(param: Param) = param.let { (id) ->
        // TODO: 권한검사 (둘 다 같은 방에 존재하는지)
        repository.sendMessage(
            listOf(memberRepository.get(id).deviceToken),
            "title",
            "message"
        )
    }

    data class Param(
        val callerMemberId: Int,
        val targetMemberId: Int
    ) : Executor.Param
}