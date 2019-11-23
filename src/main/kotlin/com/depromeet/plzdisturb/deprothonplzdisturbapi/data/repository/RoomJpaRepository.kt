package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model.RoomData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RoomJpaRepository : JpaRepository<RoomData, Int> {

    fun getByCode(@Param("code") code: String): RoomData
}