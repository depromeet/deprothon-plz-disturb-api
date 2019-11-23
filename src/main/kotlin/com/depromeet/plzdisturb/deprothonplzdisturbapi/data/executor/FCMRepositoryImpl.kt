package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository.FirebaseCloudMessage
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.FCMRepository
import org.springframework.stereotype.Component

@Component
class FCMRepositoryImpl(
    private val fcm: FirebaseCloudMessage
) : FCMRepository {

    override fun sendMessage(token: List<String>, title: String, message: String) = fcm.send(
        token, title, message
    )
}