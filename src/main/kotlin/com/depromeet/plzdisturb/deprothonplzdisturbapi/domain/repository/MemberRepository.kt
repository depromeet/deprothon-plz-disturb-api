package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : Repository {

    fun createMember(name: String, image: ImageContainer): Member
    fun getAll(sort: Sort): List<Member>
    fun getAll(ids: Iterable<Int>): List<Member>
    fun get(id: Int): Member
}
