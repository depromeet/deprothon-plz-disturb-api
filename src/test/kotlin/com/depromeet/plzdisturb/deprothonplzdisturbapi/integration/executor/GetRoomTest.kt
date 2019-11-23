package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateMember
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoom
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetRoom
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class GetRoomTest {

    @Autowired
    private lateinit var createMember: CreateMember

    @Autowired
    private lateinit var createRoom: CreateRoom

    @Autowired
    private lateinit var getRoom: GetRoom

    @Test
    fun test_execute() {
        // given
        val member = createMember.execute(
            CreateMember.Param("name", ImageContainer.NONE)
        )
        val room = createRoom.execute(
            CreateRoom.Param("group", member.id)
        )
        // when
        val target = getRoom.execute(
            GetRoom.Param(member.id, room.code)
        )
        // then
        assertThat(target).isEqualTo(room)
    }
}