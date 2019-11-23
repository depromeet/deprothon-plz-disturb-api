package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.FCMRepository
import org.springframework.stereotype.Service

@Service
class DoDisturb(
    private val repository: FCMRepository
) : Executor<DoDisturb.Param, Unit> {

    override fun execute(param: Param) = param.let { (token) ->
        repository.sendMessage(
            token,
            "title",
            "message"
        )
    }

    data class Param(
        val token: List<String>
    ) : Executor.Param
}