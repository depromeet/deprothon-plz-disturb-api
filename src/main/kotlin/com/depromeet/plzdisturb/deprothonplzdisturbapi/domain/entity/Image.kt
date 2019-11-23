package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity

sealed class ImageContainer {
    data class Image(
        val url: String
    ) : ImageContainer()

    object NONE : ImageContainer()
}