package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.room

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoom
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.Request.Request
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.response.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CreateRoomController(
    private val createRoom: CreateRoom,
    private val roomAssembler: RoomAssembler
) {
    @PostMapping("/rooms", consumes = ["application/json"])
    fun createRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestBody body: Request<String>
    ): Response<RoomResponse> =
        Response(
            roomAssembler.toRoomResponse(
                createRoom.execute(
                    CreateRoom.Param(
                        body.data,
                        id
                    )
                )

            )
        )
}