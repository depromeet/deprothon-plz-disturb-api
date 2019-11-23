package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
sealed class ImageContainer {
    @Embeddable
    data class Image(
        @Column(name = "image_url")
        val url: String
    ) : ImageContainer()

    object NONE : ImageContainer()
}