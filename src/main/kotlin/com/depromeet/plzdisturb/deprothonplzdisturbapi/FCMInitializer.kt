package com.depromeet.plzdisturb.deprothonplzdisturbapi

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class FCMInitializer {

    @PostConstruct
    fun initialize() {
        try {
            val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())).build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase application has been initialized")
            }
        } catch (e: IOException) {
            logger.error(e.message)
        }

    }

    companion object {

        private val logger = LoggerFactory.getLogger(FCMInitializer::class.java)
        private val FIREBASE_CONFIG_PATH = "google-service.json"
    }

}