package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.MemberData
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberData, Int> {
    fun findByProviderIdAndProviderUserId(providerId: String, providerUserId: String) : MemberData?
}