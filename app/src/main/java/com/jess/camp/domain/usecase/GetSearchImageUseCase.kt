package com.jess.camp.domain.usecase

import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.repository.SearchRepository

class GetSearchImageUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchEntity<ImageDocumentEntity> = repository.getSearchImage(
        query,
        sort,
        page,
        size
    )
}