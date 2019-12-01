package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.room

import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member.MemberResponse
import org.codehaus.jackson.annotate.JsonProperty

data class RoomResponse(
    val id: Int,
    val code: String,
    val name: String,
    @JsonProperty("members")
    val memberResponses: List<MemberResponse>
)
