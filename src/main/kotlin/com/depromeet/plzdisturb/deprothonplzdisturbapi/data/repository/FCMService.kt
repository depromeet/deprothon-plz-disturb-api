package com.depromeet.plzdisturb.deprothonplzdisturbapi.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.WebpushNotification
import com.google.firebase.messaging.WebpushConfig
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException


object FirebaseCloudMessage {

    private val logger = LoggerFactory.getLogger(FirebaseCloudMessage::class.java)

    private val instance = FirebaseMessaging.getInstance()

    @Throws(InterruptedException::class, ExecutionException::class)
    fun send(token: List<String>, title: String, message: String) {
        val response = instance.sendAllAsync(
            token.map {
                Message.builder()
                    .setToken(it)
                    .setWebpushConfig(
                        WebpushConfig.builder().putHeader("ttl", "300")
                            .setNotification(WebpushNotification(
                                title,
                                message)
                            ).build()
                    ).build()
            }
        ).get()

        logger.info("Sent message: $response")
    }
}