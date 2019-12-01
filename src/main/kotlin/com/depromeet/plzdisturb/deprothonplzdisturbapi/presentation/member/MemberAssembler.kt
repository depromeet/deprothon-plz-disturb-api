package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import org.springframework.stereotype.Component

@Component
class MemberAssembler {
    fun toMemberResponse(member: Member) = MemberResponse(
        member.id,
        member.name,
        member.resolveImageUrl(),
        member.providerId,
        member.providerUserId
    )
}