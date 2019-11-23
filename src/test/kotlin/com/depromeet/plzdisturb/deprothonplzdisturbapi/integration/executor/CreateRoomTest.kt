package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateMember
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoom
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
class CreateRoomTest {
    @Autowired
    private lateinit var createRoom: CreateRoom

    @Autowired
    private lateinit var createMember: CreateMember

    private lateinit var member : Member

    @Test
    fun test_execute() {
        member = createMember.execute(
            CreateMember.Param("name", ImageContainer.NONE)
        )

        // given
        val name = "name"
        // when
        val room = createRoom.execute(
            CreateRoom.Param(name, member.id)
        )
        // then
        assertThat(room.name).isEqualTo("name")
        assertThat(room.members).isNotEmpty
    }
}