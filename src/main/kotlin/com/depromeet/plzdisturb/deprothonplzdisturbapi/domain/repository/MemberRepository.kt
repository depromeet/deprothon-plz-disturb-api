package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int>