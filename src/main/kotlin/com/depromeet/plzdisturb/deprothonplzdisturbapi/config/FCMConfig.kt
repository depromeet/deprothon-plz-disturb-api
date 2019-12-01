package com.depromeet.plzdisturb.deprothonplzdisturbapi.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FCMConfig {

    companion object {

        private val FIREBASE_CONFIG_PATH = "deprothon-plz-disturb.json"
    }

    @Bean
    fun app(): FirebaseApp = FirebaseOptions.Builder()
        .setCredentials(
            GoogleCredentials.fromStream(ClassPathResource(FIREBASE_CONFIG_PATH).inputStream)
        ).build().let {
            if (FirebaseApp.getApps().isEmpty())
                FirebaseApp.initializeApp(it)
            else FirebaseApp.getApps().first()
        }
}