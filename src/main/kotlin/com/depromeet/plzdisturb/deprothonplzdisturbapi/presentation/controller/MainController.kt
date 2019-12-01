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
    private val getMember: GetMember,
    private val getMyRooms: GetMyRooms,
    private val getRoom: GetRoom,
    private val joinRoom: JoinRoom,
    private val doDisturb: DoDisturb
) {

    @GetMapping("/members", consumes = ["application/json"])
    fun getMember(@RequestAttribute("memberId") id: Int): Response<Member> =
        Response(
            getMember.execute(GetMember.Param(id))
        )

    @GetMapping("/rooms", consumes = ["application/json"])
    fun getRoom(
        @RequestAttribute("memberId") id: Int,
        @RequestParam("code") code: String
    ): Response<Room> =
        Response(
            getRoom.execute(GetRoom.Param(id, code))
        )

    @GetMapping("/me/rooms", consumes = ["application/json"])
    fun getRoom(
        @RequestAttribute("memberId") id: Int
    ): Response<List<Room>> =
        Response(
            getMyRooms.execute(GetMyRooms.Param(id))
        )

    @PostMapping("/rooms/join", consumes = ["application/json"])
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
