package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository

interface FCMRepository : Repository {
    fun sendMessage(
        token: List<String>,
        title: String,
        message: String
    )
}