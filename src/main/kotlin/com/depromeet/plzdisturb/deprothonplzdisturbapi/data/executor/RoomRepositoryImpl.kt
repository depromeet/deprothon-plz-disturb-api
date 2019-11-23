package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.RoomData
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.toEntity
import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.RoomJpaRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.RoomRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class RoomRepositoryImpl(
    private val jpaRepository: RoomJpaRepository
) : RoomRepository {

    override fun add(name: String, code: String): Room = jpaRepository.save(
        RoomData(
            null,
            code,
            name
        )
    ).let { it.toEntity(emptyList()) }

    override fun getAll(sort: Sort): List<Room> = jpaRepository.findAll(sort)
        .map { it.toEntity(emptyList()) }

    override fun getAll(ids: Iterable<Int>): List<Room> = jpaRepository.findAllById(ids)
        .map { it.toEntity(emptyList()) }

    override fun get(id: Int): Room = jpaRepository.getOne(id).toEntity(emptyList())

    override fun get(code: String): Room = jpaRepository.getByCode(code).toEntity(emptyList())
}