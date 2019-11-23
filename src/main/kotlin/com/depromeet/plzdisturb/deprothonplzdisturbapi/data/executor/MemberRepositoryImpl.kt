package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.MemberData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.toEntity
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.MemberJpaRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val jpaMemberRepository: MemberJpaRepository
) : MemberRepository {

    override fun add(name: String, image: ImageContainer): Member = jpaMemberRepository.save(
        MemberData(
            null,
            name,
            when (image) {
                is ImageContainer.Image -> image.url
                ImageContainer.NONE -> null
            }
        )
    ).let { it.toEntity() }

    override fun getAll(sort: Sort): List<Member> = jpaMemberRepository.findAll(sort)
        .map { it.toEntity() }

    override fun getAll(ids: Iterable<Int>): List<Member> = jpaMemberRepository.findAllById(ids)
        .map { it.toEntity() }

    override fun get(id: Int): Member = jpaMemberRepository.getOne(id).toEntity()
}