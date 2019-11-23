package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import org.springframework.data.domain.Sort

interface RoomRepository : Repository {

    fun add(name: String, code: String): Room
    fun getAll(sort: Sort): List<Room>
    fun getAll(ids: Iterable<Int>): List<Room>
    fun get(id: Int): Room
    fun get(code: String): Room
}