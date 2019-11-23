package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Room
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.*
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.controller.response.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
    private val createRoom: CreateRoom,
    private val getMember: GetMember,
    private val getMyRooms: GetMyRooms,
    private val getRoom: GetRoom,
    private val joinRoom: JoinRoom,
    private val doDisturb: DoDisturb
) {

    @PostMapping("/room", consumes = ["application/json"])
    fun createRoom(@RequestParam("name") name: String): Response<Room> =
        Response(
            createRoom.execute(
                CreateRoom.Param(
                    name,
                    0
                )
            )
        )

    @GetMapping("/member", consumes = ["application/json"])
    fun getMember(@RequestParam("member_id") id: Int): Response<Member> =
        Response(
            getMember.execute(GetMember.Param(id))
        )

    @GetMapping("/room", consumes = ["application/json"])
    fun getRoom(@RequestParam("code") code: String): Response<Room> =
        Response(
            getRoom.execute(GetRoom.Param(0, code))
        )

    @GetMapping("/me/room", consumes = ["application/json"])
    fun getRoom(): Response<List<Room>> =
        Response(
            getMyRooms.execute(GetMyRooms.Param(0))
        )

    @PostMapping("/room/join", consumes = ["application/json"])
    fun joinRoom(@RequestParam("code") code: String): Response<Room> =
        Response(
            joinRoom.execute(
                JoinRoom.Param(
                    0,
                    code
                )
            )
        )

    @PostMapping("/disturb", consumes = ["application/json"])
    fun disturb(@RequestParam("token") token: List<String>) =
        Response(
            doDisturb.execute(
                DoDisturb.Param(
                    token
                )
            )
        )
}