package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetMemberService(
    private val memberRepository: MemberRepository
) : Executor<GetMemberService.Param, Member> {

    @Transactional
    override fun execute(param: GetMemberService.Param) = param.let { (memberId) ->
        memberRepository.get(memberId)
    }

    data class Param(
        val memberId: Int
    ) : Executor.Param
}

