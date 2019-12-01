package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.room

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.CreateRoomService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMyRoomsService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetRoomsService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.JoinRoomService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.Request
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.Response
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api")
class RoomController(
    private val createRoomService: CreateRoomService,
    private val getRoomsService: GetRoomsService,
    private val getMyRoomsService: GetMyRoomsService,
    private val joinRoomService: JoinRoomService,
    private val roomAssembler: RoomAssembler
) {
    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestBody body: Request<String>
    ): Response<RoomResponse> =
        Response.from(
            roomAssembler.toRoomResponse(
                createRoomService.execute(
                    CreateRoomService.Param(
                        body.data,
                        id
                    )
                )
            )
        )

    @GetMapping("/rooms")
    fun getRooms(
        @RequestAttribute("memberId") id: Int,
        @RequestParam("code") code: String
    ): Response<Room> =
        Response.from(
            getRoomsService.execute(GetRoomsService.Param(id, code))
        )

    @GetMapping("/members/me/rooms")
    fun getMyRooms(
        @RequestAttribute("memberId") id: Int
    ): Response<List<RoomResponse>> =
        Response.fromList(
            getMyRoomsService.execute(
                GetMyRoomsService.Param(id)
            ).stream()
                .map { room -> roomAssembler.toRoomResponse(room) }
                .collect(Collectors.toList())
        )

    @PostMapping("/rooms/join")
    fun joinRoom(
        @RequestAttribute("memberId") memberId: Int,
        @RequestBody body: Request<String>
    ): Response<Room> =
        Response.from(
            joinRoomService.execute(
                JoinRoomService.Param(
                    memberId,
                    body.data
                )
            )
        )
}