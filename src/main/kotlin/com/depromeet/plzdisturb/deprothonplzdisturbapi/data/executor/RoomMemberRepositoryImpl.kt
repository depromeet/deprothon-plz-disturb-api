package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.RoomMemberData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.toDataModel
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.toEntity
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.RoomMemberJpaRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomMemberRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class RoomMemberRepositoryImpl(
    private val jpaRepository: RoomMemberJpaRepository
) : RoomMemberRepository {

    override fun add(room: Room, member: Member): Pair<Room, Member> = jpaRepository.save(
        RoomMemberData(
            null,
            member.toDataModel(),
            room.toDataModel()
        )
    ).let { it.room.toEntity(listOf(it.member)) to it.member.toEntity() }

    override fun getMembers(room: Room): List<Member> = jpaRepository.findByRoom(room.toDataModel())
        .map {
            it.member.toEntity()
        }

    override fun getRooms(member: Member): List<Room> = jpaRepository.findByMember(member.toDataModel())
        .map {
            it.room.toEntity(emptyList())
        }
}