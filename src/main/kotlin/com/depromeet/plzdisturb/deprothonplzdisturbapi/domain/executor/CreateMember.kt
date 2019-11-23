package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class CreateMember(
    private val memberRepository: MemberRepository
) : Executor<CreateMember.Param, Member> {

    override fun execute(param: Param): Member = param.let { (name, image) ->
        memberRepository.add(name, image)
    }

    data class Param(
        val name: String,
        val image: ImageContainer
    ) : Executor.Param
}