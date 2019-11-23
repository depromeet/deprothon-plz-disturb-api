package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

interface Executor<T : Executor.Param, R> {

    fun execute(param: T): R

    interface Param
}