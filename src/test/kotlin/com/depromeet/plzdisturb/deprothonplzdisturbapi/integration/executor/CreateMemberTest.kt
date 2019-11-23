package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateMember
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class CreateMemberTest {
    @Autowired
    private lateinit var memberService: CreateMember
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun test_execute() {
        // given
        val name = "name";
        val imageUrl = "imageUrl"
        val imageContainer = ImageContainer.Image(imageUrl)
        // when
        val member = memberService.execute(
            CreateMember.Param(name, imageContainer)
        )
        // then
        assertThat(member.name).isEqualTo("name")
        assertThat(member.imageUrl).isEqualTo(imageContainer)
        assertThat(memberRepository.get(member.id)).isNotNull
    }
}