package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.FirebaseCloudMessage
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.FCMRepository

class FCMRepositoryImpl : FCMRepository {

    override fun sendMessage(token: List<String>, title: String, message: String) = FirebaseCloudMessage.send(
        token, title, message
    )
}