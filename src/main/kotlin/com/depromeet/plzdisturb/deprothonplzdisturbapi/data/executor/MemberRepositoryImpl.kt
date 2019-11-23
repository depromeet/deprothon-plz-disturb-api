package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.MemberData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.MemberJpaRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val jpaMemberRepository: MemberJpaRepository
) : MemberRepository {

    override fun createMember(name: String, imageUrl: ImageContainer): Member = jpaMemberRepository.save(
        MemberData(
            null,
            name,
            imageUrl
        )
    ).run {
        Member(
            id!!,
            name,
            imageUrl
        )
    }
}