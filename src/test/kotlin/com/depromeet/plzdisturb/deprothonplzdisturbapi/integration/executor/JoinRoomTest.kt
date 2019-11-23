package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.*
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
class JoinRoomTest {

    @Autowired
    private lateinit var createMember: CreateMember

    @Autowired
    private lateinit var createRoom: CreateRoom

    @Autowired
    private lateinit var joinRoom: JoinRoom

    @Test
    fun test_execute() {
        // given
        val member = createMember.execute(
            CreateMember.Param("name", ImageContainer.NONE)
        )
        val room = createRoom.execute(
            CreateRoom.Param("group", member.id)
        )
        val member2 = createMember.execute(
            CreateMember.Param("name2", ImageContainer.NONE)
        )
        // when
        val target = joinRoom.execute(
            JoinRoom.Param(member2.id, room.code)
        )
        // then
        assertThat(target).isEqualTo(room)
    }
}