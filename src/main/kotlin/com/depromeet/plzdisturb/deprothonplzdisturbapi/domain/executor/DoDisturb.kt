package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.FCMRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class DoDisturb(
    private val repository: FCMRepository,
    private val memberRepository: MemberRepository
) : Executor<DoDisturb.Param, Unit> {

    override fun execute(param: Param) = param.let { (id) ->
        repository.sendMessage(
            listOf(memberRepository.get(id).deviceToken),
            "title",
            "message"
        )
    }

    data class Param(
        val id: Int
    ) : Executor.Param
}