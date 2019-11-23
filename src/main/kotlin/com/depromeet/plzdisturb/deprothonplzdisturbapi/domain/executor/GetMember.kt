package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class GetMember(
    private val memberRepository: MemberRepository
) : Executor<GetMember.Param, Member> {

    override fun execute(param: Param): Member = param.let { (memberId) ->
        memberRepository.get(memberId)
    }

    data class Param(
        val memberId: Int
    ) : Executor.Param
}