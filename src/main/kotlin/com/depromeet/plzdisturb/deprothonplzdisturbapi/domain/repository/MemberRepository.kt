package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : Repository {

    fun createMember(name: String, image: ImageContainer): Member
}
