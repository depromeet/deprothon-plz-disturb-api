package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class RoomData(
        @Id
        @GeneratedValue
        val id: Int?,
        val code: String,
        val name: String
)
