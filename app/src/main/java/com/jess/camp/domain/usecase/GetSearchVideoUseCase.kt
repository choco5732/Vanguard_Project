package com.jess.camp.domain.usecase

import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.model.VideoDocumentEntity
import com.jess.camp.domain.repository.SearchRepository

class GetSearchVideoUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchEntity<VideoDocumentEntity> = repository.getSearchVideo(
        query,
        sort,
        page,
        size
    )
}