package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.*
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.Request.Request
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.response.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MainController(
    private val createRoom: CreateRoom,
    private val getMember: GetMember,
    private val getMyRooms: GetMyRooms,
    private val getRoom: GetRoom,
    private val joinRoom: JoinRoom,
    private val doDisturb: DoDisturb
) {

    @PostMapping("/room", consumes = ["application/json"])
    fun createRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestBody body: Request<String>
    ): Response<Room> =
        Response(
            createRoom.execute(
                CreateRoom.Param(
                    body.data,
                    id
                )
            )
        )

    @GetMapping("/member", consumes = ["application/json"])
    fun getMember(@RequestAttribute("memberId") id: Int): Response<Member> =
        Response(
            getMember.execute(GetMember.Param(id))
        )

    @GetMapping("/room", consumes = ["application/json"])
    fun getRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestParam("code") code: String
    ): Response<Room> =
        Response(
            getRoom.execute(GetRoom.Param(id, code))
        )

    @GetMapping("/me/room", consumes = ["application/json"])
    fun getRoom(
        @RequestAttribute("memberId") id: Int
    ): Response<List<Room>> =
        Response(
            getMyRooms.execute(GetMyRooms.Param(id))
        )

    @PostMapping("/room/join", consumes = ["application/json"])
    fun joinRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestBody body: Request<String>
    ): Response<Room> =
        Response(
            joinRoom.execute(
                JoinRoom.Param(
                    id,
                    body.data
                )
            )
        )

    @PostMapping("/disturb", consumes = ["application/json"])
    fun disturb(@RequestParam body: Request<Int>) =
        Response(
            doDisturb.execute(
                DoDisturb.Param(
                    body.data
                )
            )
        )
}
