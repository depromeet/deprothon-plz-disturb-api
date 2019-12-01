package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetMembersService(
    private val memberRepository: MemberRepository
) : Executor<GetMembersService.Param, Page<Member>> {

    @Transactional
    override fun execute(param: Param): Page<Member> = param.let { (pageable) ->
        memberRepository.findAll(pageable)
    }

    data class Param(
        val pageable: Pageable
    ) : Executor.Param
}

