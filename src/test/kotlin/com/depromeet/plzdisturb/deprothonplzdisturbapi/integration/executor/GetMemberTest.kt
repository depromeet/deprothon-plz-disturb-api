package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateMember
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoom
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMember
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class GetMemberTest {

    @Autowired
    private lateinit var createMember: CreateMember

    @Autowired
    private lateinit var getMember: GetMember

    @Test
    fun test_execute() {
        // given
        val member = createMember.execute(
            CreateMember.Param("name", ImageContainer.NONE)
        )
        // when
        val target = getMember.execute(
            GetMember.Param(member.id)
        )
        // then
        assertThat(target).isEqualTo(member)
    }
}