package com.jess.camp.domain.repository

import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) : SearchEntity<ImageDocumentEntity>
}