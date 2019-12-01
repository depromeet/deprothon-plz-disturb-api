package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.member

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.DoDisturbService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMemberService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.GetMembersService
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.Response
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MemberController(
    private val getMemberService: GetMemberService,
    private val getMembersService: GetMembersService,
    private val doDisturbService: DoDisturbService,
    private val memberAssembler: MemberAssembler
) {
    @GetMapping("/members/{memberId}")
    fun getMe(
        @RequestAttribute("memberId") callerMemberId: Int,
        @PathVariable memberId: Int
    ): Response<MemberResponse> =
        Response.from(
            memberAssembler.toMemberResponse(
                getMemberService.execute(
                    GetMemberService.Param(memberId)
                )
            )
        )

    @GetMapping("/members")
    fun getMe(
        @RequestAttribute("memberId") memberId: Int,
        @PageableDefault(size = 20) pageable: Pageable
    ): Response<List<MemberResponse>> =
        Response.from(
            getMembersService.execute(
                GetMembersService.Param(pageable)
            )
                .map { member -> memberAssembler.toMemberResponse(member) }
        )

    @GetMapping("/members/me")
    fun getMe(
        @RequestAttribute("memberId") memberId: Int
    ): Response<MemberResponse> =
        Response.from(
            memberAssembler.toMemberResponse(
                getMemberService.execute(
                    GetMemberService.Param(memberId)
                )
            )
        )

    @PostMapping("members/{memberId}/disturb")
    fun disturb(
        @RequestAttribute("memberId") callerMemberId: Int,
        @PathVariable memberId: Int
    ): Response<Unit> =
        Response.from(
            doDisturbService.execute(
                DoDisturbService.Param(
                    callerMemberId,
                    memberId
                )
            )
        )
}