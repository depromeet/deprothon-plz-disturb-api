package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.domain.Page

class Response<T>(
    val data: T,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val totalCount: Long?
) {
    companion object {
        fun <T> from(data: T): Response<T> = Response(data, null)

        fun <T> from(dataPage: Page<T>): Response<List<T>> = Response(dataPage.toList(), dataPage.totalElements)

        fun <T> fromList(dataList: List<T>): Response<List<T>> = Response(dataList, null)
    }
}
